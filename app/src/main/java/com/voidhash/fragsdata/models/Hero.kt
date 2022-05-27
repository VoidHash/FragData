package com.voidhash.fragsdata.models

data class Hero(
    var name: String,
    var race: String,
    var fragment: String,
    var items: List<Item>
) {
    companion object {
        var instance: Hero? = null
    }
}

data class Item(
    var name: String
)