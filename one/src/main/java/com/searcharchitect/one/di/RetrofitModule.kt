package com.searcharchitect.one.di

import com.searcharchitect.one.BuildConfig
import com.searcharchitect.one.di.qualifer.QFacebookRetrofit
import com.searcharchitect.one.di.qualifer.QGoogleSheetsRetrofit
import com.searcharchitect.one.di.qualifer.QVkRetrofit
import com.searcharchitect.one.retrofit.api.IFacebookApi
import com.searcharchitect.one.retrofit.api.IGoogleSheetsApi
import com.searcharchitect.one.retrofit.api.IVkApi
import com.searcharchitect.one.utility.Constant.FACEBOOK_BASE_URL
import com.searcharchitect.one.utility.Constant.GOOGLE_SHEETS_BASE_URL
import com.searcharchitect.one.utility.Constant.VK_BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

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

    //@ExperimentalSerializationApi
    @Provides
    @Singleton
    @QGoogleSheetsRetrofit
    fun provideGoogleSheetsRetrofit(okHttpClient: OkHttpClient): Retrofit {
        //val json = Json { ignoreUnknownKeys = true }

        return Retrofit.Builder()
            .baseUrl(GOOGLE_SHEETS_BASE_URL)
            .client(okHttpClient)
            //.addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addConverterFactory(GsonConverterFactory.create())
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
    @QFacebookRetrofit
    fun provideFacebookRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(FACEBOOK_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    fun provideFacebookApi(@QFacebookRetrofit retrofit: Retrofit): IFacebookApi {
        return retrofit.create(IFacebookApi::class.java)
    }

    @Provides
    @Singleton
    @QVkRetrofit
    fun provideVkRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(VK_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    @Provides
    fun provideVkApi(@QVkRetrofit retrofit: Retrofit): IVkApi {
        return retrofit.create(IVkApi::class.java)
    }

}