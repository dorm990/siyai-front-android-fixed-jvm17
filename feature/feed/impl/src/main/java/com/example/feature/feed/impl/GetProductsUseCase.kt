package com.example.feature.feed.impl

import com.example.core.network.ProductsApi
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val api: ProductsApi
) {
    suspend fun execute() = api.getProducts().products
}
