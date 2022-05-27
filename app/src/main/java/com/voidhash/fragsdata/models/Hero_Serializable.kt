package com.voidhash.fragsdata.models

import java.io.Serializable

data class Hero_Serializable(
    var name: String,
    var race: String,
    var fragment: String,
    var items: List<Item_Serializable>
) : Serializable

data class Item_Serializable(
    var name: String
) : Serializable