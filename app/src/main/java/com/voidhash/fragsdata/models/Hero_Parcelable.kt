package com.voidhash.fragsdata.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hero_Parcelable(
    var name: String,
    var race: String,
    var fragment: String,
    var items: List<Item_Parcelable>
) : Parcelable

@Parcelize
data class Item_Parcelable(
    var name: String
) : Parcelable