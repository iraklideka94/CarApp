package com.example.rvlesson

import java.util.*

data class Item(
    var id: UUID,
    var imageUrl: String,
    var brand: String,
    var model: String
){
    override fun equals(other: Any?): Boolean {
        if(this === other)return true
        if (javaClass != other?.javaClass)return false

        other as Item

        if(id != other.id)return false
        if(imageUrl != other.imageUrl)return false
        if(brand!= other.brand)return false
        if(model != other.model)return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + brand.hashCode()
        result = 31 * result + model.hashCode()
        return result
    }
}
