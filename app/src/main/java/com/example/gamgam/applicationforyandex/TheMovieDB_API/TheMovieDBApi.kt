package com.example.gamgam.applicationforyandex.TheMovieDB_API

import io.reactivex.Observable
import retrofit2.http.GET

interface TheMovieDBApi {
    @GET("https://api.themoviedb.org/3/movie/popular?page=1&language=ru&api_key=23197e6016670687445147376c72dc68")
    fun getDate():Observable<InfoModel>
}