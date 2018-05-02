package com.example.gamgam.applicationforyandex

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class InfoModel (val page: Int, val totalResults: Int, val totalPages: Int, val results: List<Result>)

data class Result (val title: String,@SerializedName("poster_path")  val posterPath: String):Parcelable{
    var urlSmallPoster:String = ""
        get() = "https://image.tmdb.org/t/p/w342/$posterPath"
    var urlBigPoster:String=""
        get() = "https://image.tmdb.org/t/p/original/$posterPath"

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(posterPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }


}