package com.example.rickandmorty.pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
class Location (
        @SerializedName("name")
        var name: String?
        ) : Parcelable {
        constructor(parcel: Parcel) : this(parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(name)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Location> {
                override fun createFromParcel(parcel: Parcel): Location {
                        return Location(parcel)
                }

                override fun newArray(size: Int): Array<Location?> {
                        return arrayOfNulls(size)
                }
        }
}