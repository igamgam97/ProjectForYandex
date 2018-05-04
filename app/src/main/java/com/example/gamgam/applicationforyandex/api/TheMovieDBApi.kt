package com.example.gamgam.applicationforyandex.api

import com.example.gamgam.applicationforyandex.models.InfoModel
import io.reactivex.Observable
import retrofit2.http.GET

interface TheMovieDBApi {
    //only one request - full path
    @GET("https://api.themoviedb.org/3/movie/popular?page=1&language=ru&api_key=23197e6016670687445147376c72dc68")
    fun getDate():Observable<InfoModel>
}