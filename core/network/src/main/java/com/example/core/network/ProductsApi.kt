package com.example.core.network

import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String
)

@Serializable
data class ProductsResponseDto(
    val products: List<ProductDto>
)

interface ProductsApi {
    @GET("products")
    suspend fun getProducts(): ProductsResponseDto

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: Int): ProductDto
}
