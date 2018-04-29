package com.example.gamgam.applicationforyandex

import com.google.gson.annotations.SerializedName


data class InfoModel (val page: Int, val totalResults: Int, val totalPages: Int, val results: List<Result>)

data class Result (@SerializedName("vote_count") val voteCount: Int?, val id: Int, val video: Boolean, val voteAverage: Double,
                   val title: String, val popularity: Double, val posterPath: String, val originalLanguage: String,
                   val originalTitle: String, val genreIds: List<Int>, val backdropPath: String, val adult: Boolean,
                   val overview: String, val releaseDate: String){}