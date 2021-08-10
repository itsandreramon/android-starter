package de.thb.rulona.di

import android.content.Context
import de.thb.rulona.App
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

val appModule = module {

    fun provideApplicationScope(
        applicationContext: Context
    ): CoroutineScope {
        return (applicationContext as App).applicationScope
    }

    single { provideApplicationScope(get()) }
}
