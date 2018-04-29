package com.example.gamgam.applicationforyandex

import android.os.Parcel
import android.os.Parcelable

class ImageSpace(val url:String, val title:String):Parcelable{
    private constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageSpace> {
        override fun createFromParcel(parcel: Parcel): ImageSpace {
            return ImageSpace(parcel)
        }

        override fun newArray(size: Int): Array<ImageSpace?> {
            return arrayOfNulls(size)
        }

        fun getSpacePhotos(): Array<ImageSpace> {

            return arrayOf(ImageSpace("http://i.imgur.com/zuG2bGQ.jpg", "Galaxy"),
                    ImageSpace("http://i.imgur.com/ovr0NAF.jpg", "Space Shuttle"),
                    ImageSpace("http://i.imgur.com/n6RfJX2.jpg", "Galaxy Orion"),
                    ImageSpace("http://i.imgur.com/qpr5LR2.jpg", "Earth"),
                    ImageSpace("http://i.imgur.com/pSHXfu5.jpg", "Astronaut"),
                    ImageSpace("http://i.imgur.com/3wQcZeY.jpg", "Satellite"))
        }
    }



}