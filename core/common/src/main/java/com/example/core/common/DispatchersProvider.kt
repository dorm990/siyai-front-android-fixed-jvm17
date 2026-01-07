package com.example.core.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatchersProvider {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
}

class DefaultDispatchersProvider @Inject constructor() : DispatchersProvider {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
}
