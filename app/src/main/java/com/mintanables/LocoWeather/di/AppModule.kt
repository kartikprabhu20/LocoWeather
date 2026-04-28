package com.mintanables.LocoWeather.di

import com.mintanables.LocoWeather.data.Constants
import com.mintanables.LocoWeather.data.repository.WeatherRepositoryImpl
import com.mintanables.LocoWeather.data.source.remote.WeatherRemoteSource
import com.mintanables.LocoWeather.data.source.remote.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return OkHttpClient.Builder().addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(client: OkHttpClient): WeatherService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRemoteSource: WeatherRemoteSource): WeatherRepositoryImpl {
        return WeatherRepositoryImpl(weatherRemoteSource)
    }

    @Provides
    @Singleton
    fun provideWeatherRemoteDataSource(weatherService: WeatherService, settingsRepository: com.mintanables.LocoWeather.domain.repository.SettingsRepository): WeatherRemoteSource {
        return WeatherRemoteSource(weatherService, settingsRepository)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context): android.content.SharedPreferences {
        return context.getSharedPreferences("LocoWeatherPrefs", android.content.Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(sharedPreferences: android.content.SharedPreferences): com.mintanables.LocoWeather.domain.repository.SettingsRepository {
        return com.mintanables.LocoWeather.data.repository.SettingsRepositoryImpl(sharedPreferences)
    }
}