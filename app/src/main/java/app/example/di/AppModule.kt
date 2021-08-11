package app.example.di

import android.content.Context
import app.example.App
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
