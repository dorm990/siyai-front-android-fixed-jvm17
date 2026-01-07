package com.example.feature.feed.impl

import com.example.core.network.ProductDto
import com.example.core.network.ProductsApi
import com.example.core.network.ProductsResponseDto
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetProductsUseCaseTest {
    private val api = mockk<ProductsApi>()
    private val uc = GetProductsUseCase(api)

    @Test fun returns_products() = runTest {
        coEvery { api.getProducts() } returns ProductsResponseDto(listOf(ProductDto(1, "T", "D", "url")))
        val res = uc.execute()
        assertEquals(1, res.size)
        assertEquals(1, res.first().id)
    }
}
