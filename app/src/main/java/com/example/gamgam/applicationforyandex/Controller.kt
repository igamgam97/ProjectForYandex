package com.example.gamgam.applicationforyandex

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Controller {
    fun getApi(): TheMovieDBApi {
        val Base_URL = "https://api.themoviedb.org/"
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(TheMovieDBApi::class.java)
    }
}