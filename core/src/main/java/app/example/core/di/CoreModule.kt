package app.example.core.di

import android.content.Context
import androidx.room.Room
import app.example.core.data.AppDatabase
import app.example.core.util.CoroutinesDispatcherProvider
import app.example.core.util.DefaultDispatcherProvider
import org.koin.dsl.module

val coreModule = module {

    fun provideAppDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "db"
        ).build()
    }

    single { provideAppDatabase(get()) }
    single<CoroutinesDispatcherProvider> { DefaultDispatcherProvider() }
}
