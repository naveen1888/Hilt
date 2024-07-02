package com.rawat.hilt.retrofit

import com.rawat.hilt.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface FakerAPI {

    @GET("products")
    suspend fun getProducts() : List<Product>
}