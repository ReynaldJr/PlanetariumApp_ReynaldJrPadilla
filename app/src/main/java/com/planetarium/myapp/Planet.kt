package com.planetarium.myapp

import android.os.Parcel
import android.os.Parcelable

data class Planet(
    val image: Int,
    val title: String,
    val shortDescription: String,
    val cardBackground: Int,
    val backgroundImage: Int,
    val longDescription: String,
    val day: String,
    val year: String,
    val radius: String,
    val planetType: String,
    val moons: String,
    val gallery_1: Int,
    val gallery_2: Int,
    val gallery_3: Int,
    val planetSound: String,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(title)
        parcel.writeString(shortDescription)
        parcel.writeInt(cardBackground)
        parcel.writeInt(backgroundImage)
        parcel.writeString(longDescription)
        parcel.writeString(day)
        parcel.writeString(year)
        parcel.writeString(radius)
        parcel.writeString(planetType)
        parcel.writeString(moons)
        parcel.writeInt(gallery_1)
        parcel.writeInt(gallery_2)
        parcel.writeInt(gallery_3)
        parcel.writeString(planetSound)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Planet> {
        override fun createFromParcel(parcel: Parcel): Planet {
            return Planet(parcel)
        }

        override fun newArray(size: Int): Array<Planet?> {
            return arrayOfNulls(size)
        }
    }

}
