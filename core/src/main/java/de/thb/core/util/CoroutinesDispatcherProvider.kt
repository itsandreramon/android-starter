package de.thb.core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

interface CoroutinesDispatcherProvider {
    fun main(): CoroutineDispatcher
    fun computation(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun database(): CoroutineDispatcher
}

class DefaultDispatcherProvider : CoroutinesDispatcherProvider {
    override fun main() = Dispatchers.Main
    override fun computation() = Dispatchers.Default
    override fun io() = Dispatchers.IO
    override fun database() = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
}
