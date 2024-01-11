package com.example.waterreservoir.config

import com.example.waterreservoir.model.Hasil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class dataconfig {

    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }

    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://rk54tnpc-3000.asse.devtunnels.ms/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getWaterReservoirService() = getRetrofit().create(WaterReservoir::class.java)


    }

    interface WaterReservoir {
        @GET("read_all_latest_data")
        fun getDataWater(): Call<Hasil>
    }