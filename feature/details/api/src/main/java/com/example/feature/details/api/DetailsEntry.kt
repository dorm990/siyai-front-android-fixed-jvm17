package com.example.feature.details.api

import cafe.adriel.voyager.core.screen.Screen
import com.example.feature.details.impl.DetailsScreen

object DetailsEntry {
    fun start(productId: Int): Screen = DetailsScreen(productId)
}
