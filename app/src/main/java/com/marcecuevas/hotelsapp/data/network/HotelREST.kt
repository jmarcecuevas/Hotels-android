package com.marcecuevas.hotelsapp.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.marcecuevas.hotelsapp.data.model.DTO.HotelDTO
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface HotelREST {

    @GET(value = "hotels")
    fun getHotels(): Deferred<Response<HotelDTO>>

    @GET(value = "hotels/{id}")
    fun getHotelDetail(@Path(value = "id") id: String): Deferred<Response<HotelDTO>>


    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): HotelREST {
            val requestInterceptor = Interceptor {chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://private-a2ba2-jovenesdealtovuelo.apiary-mock.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HotelREST::class.java)
        }
    }
}