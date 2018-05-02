package com.example.gamgam.applicationforyandex

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Controller {
    fun getApi(context: Context): TheMovieDBApi {
        val Base_URL = "https://api.themoviedb.org/"
        val client =OkHttpClient.Builder().addInterceptor(ConnectivityInterceptor(context)).build()
        val retrofit = Retrofit.Builder()
                .baseUrl(Base_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(TheMovieDBApi::class.java)
    }
}