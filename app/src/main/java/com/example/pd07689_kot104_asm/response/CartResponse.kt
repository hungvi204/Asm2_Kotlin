package com.example.pd07689_kot104_asm.response

import com.google.gson.annotations.SerializedName

data class CartResponse (
    @SerializedName("_id") val idCart: String,
    val idProduct: String,
    val image: String,
    val quantity: Int,
    val name: String,
    val price: Float
)


