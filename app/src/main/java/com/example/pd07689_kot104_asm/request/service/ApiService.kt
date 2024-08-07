package com.example.pd07689_kot104_asm.request.service

import com.example.pd07689_kot104_asm.request.CartRequest
import com.example.pd07689_kot104_asm.request.LoginRequest
import com.example.pd07689_kot104_asm.request.RegisterRequest
import com.example.pd07689_kot104_asm.response.CartResponse
import com.example.pd07689_kot104_asm.response.CategoryResponse
import com.example.pd07689_kot104_asm.response.ProductResponse
import com.example.pd07689_kot104_asm.response.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    //Auth
    @POST("register")
    suspend fun register(@Body registerReq: RegisterRequest): Response?

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response?

    // Category
    @GET("category/get-list")
    suspend fun getCategories(): List<CategoryResponse>

    //Product
    @GET("product/get-list")
    suspend fun getProducts(@Query("cateID") cateID: String?): List<ProductResponse>

    @GET("product/get-product-by-id/{productID}")
    suspend fun getProductByID(@Path("productID") productID: String?): ProductResponse?

    //Cart
    @GET("cart/get-list/{idUser}")
    suspend fun getCart(@Path("idUser") idUser: String?): List<CartResponse>

    @POST("cart/add")
    suspend fun addCart(@Body cartReq: CartRequest): CartResponse?

    @POST("cart/decrease")
    suspend fun descreaseCart(@Body cartReq: CartRequest): CartResponse?

    @POST("cart/delete-by-id")
    suspend fun deleteCartById(@Body cartReq: CartRequest): CartResponse?
}