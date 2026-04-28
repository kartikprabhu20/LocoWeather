package com.mintanables.LocoWeather.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mintanables.LocoWeather.data.Constants.TAG
import com.mintanables.LocoWeather.domain.model.Weather
import com.mintanables.LocoWeather.presentation.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


/***
 * Class to manage remote source. In this case only 1 endpoint was used, in cases where multiple endpoints are triggered changes can be added here.
 */
class WeatherRemoteSource @Inject constructor(private val weatherService: WeatherService) : RemoteDataSource {

    private val _weatherResponse = MutableLiveData<WeatherResponse>() // can be changed
    override val weatherResponse: MutableLiveData<WeatherResponse>
        get() = _weatherResponse

    override suspend fun getWeatherInfoByLocation(location: String){
        val call = weatherService.getWeatherInfoByLocation(location)
        call.enqueue(object : Callback<Weather?> {
            override fun onResponse(call: Call<Weather?>?, response: Response<Weather?>) {
                val weather = response.body()
                _weatherResponse.postValue(weather?.let { WeatherResponse.Success(it) })
            }

            override fun onFailure(call: Call<Weather?>?, t: Throwable?) {

                Log.e(TAG, "getWeather Error: ${t.toString()}")
                _weatherResponse.postValue( WeatherResponse.Error("getWeather Error: ${t.toString()}"))
            }
        })
    }

}
