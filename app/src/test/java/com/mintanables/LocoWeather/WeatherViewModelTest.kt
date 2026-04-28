package com.mintanables.LocoWeather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mintanables.LocoWeather.data.Constants
import com.mintanables.LocoWeather.data.repository.WeatherRepositoryImpl
import com.mintanables.LocoWeather.data.source.remote.WeatherRemoteSource
import com.mintanables.LocoWeather.data.source.remote.WeatherService
import com.mintanables.LocoWeather.presentation.WeatherViewModel
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

@ExperimentalCoroutinesApi
class MainCoroutineRule(private val dispatcher: TestDispatcher = StandardTestDispatcher()) :
    TestWatcher() {

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

@RunWith(JUnit4::class)
class WeatherViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    val mockWebServer = MockWebServer()
    private lateinit var weatherRemoteSource: WeatherRemoteSource
    private lateinit var weatherRepository: WeatherRepositoryImpl
    private lateinit var viewModel: WeatherViewModel

    @Mock
    var currentTemperature: Observer<String>? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mockWebServer.start()

        val client = OkHttpClient
            .Builder()
            .build()
        val weatherService =  Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)

        val settingsRepository = org.mockito.Mockito.mock(com.mintanables.LocoWeather.domain.repository.SettingsRepository::class.java)
        org.mockito.Mockito.`when`(settingsRepository.getLocation()).thenReturn("")
        org.mockito.Mockito.`when`(settingsRepository.getUnit()).thenReturn("imperial")

        weatherRemoteSource = WeatherRemoteSource(weatherService, settingsRepository)
        weatherRepository = WeatherRepositoryImpl(weatherRemoteSource)
        viewModel = WeatherViewModel(weatherRepository, settingsRepository)
    }

//    @Test
//    fun testNull() {
//        runBlocking { viewModel.getWeatherInfoByLocation("${Constants.BERLIN_LATITUDE},${Constants.BERLIN_LONGITUDE}") }
//
//        assertNotNull(weatherRepository.weatherResponse)
//        assertTrue(weatherRepository.weatherResponse.hasObservers())
//    }

    @Test
    fun `read sample success json file`() = runBlocking {
        val weatherContent = File("src/main/res/mock_weather.txt").readText()
        val forecastContent = File("src/main/res/mock_forecast.txt").readText()

        mockWebServer.dispatcher = object : okhttp3.mockwebserver.Dispatcher() {
            override fun dispatch(request: okhttp3.mockwebserver.RecordedRequest): MockResponse {
                if (request.path?.contains("weather") == true) {
                    return MockResponse().setResponseCode(200).setBody(weatherContent)
                } else if (request.path?.contains("forecast") == true) {
                    return MockResponse().setResponseCode(200).setBody(forecastContent)
                }
                return MockResponse().setResponseCode(404)
            }
        }

        viewModel.getWeatherInfoByLocation("${Constants.BERLIN_LATITUDE},${Constants.BERLIN_LONGITUDE}")

        // Give it some time to process the response
        kotlinx.coroutines.delay(2000)

        val temp = viewModel.currentTemperature.value
        println("DEBUG: Temperature value is '$temp'")
        assertTrue("Expected 42.60 but got '$temp'", temp.contains("42.60"))
    }
}