package com.fiqsky.githubuserapp

import android.os.Parcel
import android.os.Parcelable

data class User(
    val avatar: Int,
    val name: String,
    val username: String,
    val location: String,
    val repository: String,
    val company: String,
    val followers: String,
    val following: String


) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(avatar)
        dest.writeString(name)
        dest.writeString(username)
        dest.writeString(location)
        dest.writeString(repository)
        dest.writeString(company)
        dest.writeString(followers)
        dest.writeString(following)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}