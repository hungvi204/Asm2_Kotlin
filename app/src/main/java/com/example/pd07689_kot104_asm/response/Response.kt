package com.example.pd07689_kot104_asm.response

data class Response(
    val status: Int,
    val message: String,
    val data: Data?
)

data class Data(
    val _id: String,
)
