package com.example.pd07689_kot104_asm.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("_id") val productID: String,
    val productName: String,
    val price: Int,
    val image: String,
    val description: String,
    val cateID: String,
    val createdAt: String,
    val updatedAt: String,
    val __v: Int
)