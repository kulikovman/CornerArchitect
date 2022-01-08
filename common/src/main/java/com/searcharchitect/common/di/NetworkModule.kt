package com.searcharchitect.common.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.searcharchitect.common.BuildConfig
import com.searcharchitect.common.di.qualifer.QGoogleSheetsRetrofit
import com.searcharchitect.common.di.qualifer.QVkRetrofit
import com.searcharchitect.common.retrofit.api.IGoogleSheetsApi
import com.searcharchitect.common.retrofit.api.IVkApi
import com.searcharchitect.common.utility.Constant.GOOGLE_SHEETS_BASE_URL
import com.searcharchitect.common.utility.Constant.VK_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val myInterceptor = HttpLoggingInterceptor()
            myInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(myInterceptor)
        }

        return builder.build()
    }


    @Provides
    @Singleton
    @QGoogleSheetsRetrofit
    fun provideGoogleSheetsRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val jsonContentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(GOOGLE_SHEETS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(jsonContentType))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    fun provideGoogleSheetsApi(@QGoogleSheetsRetrofit retrofit: Retrofit): IGoogleSheetsApi {
        return retrofit.create(IGoogleSheetsApi::class.java)
    }


    @Provides
    @Singleton
    @QVkRetrofit
    fun provideVkRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val jsonContentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(VK_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(jsonContentType))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    fun provideVkApi(@QVkRetrofit retrofit: Retrofit): IVkApi {
        return retrofit.create(IVkApi::class.java)
    }

}