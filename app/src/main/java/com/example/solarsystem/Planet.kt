package com.example.solarsystem

import android.os.Parcel
import android.os.Parcelable

data class Planet(val image: Int, val title: String, val shortDescription: String, val cardBackground: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(title)
        parcel.writeString(shortDescription)
        parcel.writeInt(cardBackground)
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
