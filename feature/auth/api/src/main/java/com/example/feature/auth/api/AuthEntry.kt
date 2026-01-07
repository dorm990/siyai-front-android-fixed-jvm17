package com.example.feature.auth.api

import cafe.adriel.voyager.core.screen.Screen
import com.example.feature.auth.impl.LoginScreen

object AuthEntry {
    fun start(): Screen = LoginScreen()
}
