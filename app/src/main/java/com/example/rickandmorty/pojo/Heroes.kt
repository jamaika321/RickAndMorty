package com.example.rickandmorty.pojo

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Heroes (
        @PrimaryKey
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String?,
        @SerializedName("status")
        var status: String?,
        @SerializedName("species")
        var species: String?,
        @SerializedName("gender")
        var gender: String?,
        @SerializedName("image")
        var image: String?,
        @SerializedName("location")
        var location: Location?,
        @SerializedName("episode")
        var episode: Array<String>?

): Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(Location::class.java.classLoader),
                parcel.createStringArray()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(id)
                parcel.writeString(name)
                parcel.writeString(status)
                parcel.writeString(species)
                parcel.writeString(gender)
                parcel.writeString(image)
                parcel.writeParcelable(location, flags)
                parcel.writeStringArray(episode)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Heroes> {
                override fun createFromParcel(parcel: Parcel): Heroes {
                        return Heroes(parcel)
                }

                override fun newArray(size: Int): Array<Heroes?> {
                        return arrayOfNulls(size)
                }
        }
}