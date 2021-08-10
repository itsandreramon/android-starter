package de.thb.core.di

import android.content.Context
import androidx.room.Room
import de.thb.core.data.AppDatabase
import de.thb.core.prefs.PrefsStore
import de.thb.core.prefs.PrefsStoreImpl
import de.thb.core.util.CoroutinesDispatcherProvider
import de.thb.core.util.DefaultDispatcherProvider
import org.koin.dsl.module

val coreModule = module {

    fun provideAppDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "db"
        ).build()
    }

    fun providePrefsStore(applicationContext: Context): PrefsStore {
        return PrefsStoreImpl(applicationContext)
    }

    single { provideAppDatabase(get()) }
    single { providePrefsStore(get()) }
    single<CoroutinesDispatcherProvider> { DefaultDispatcherProvider() }
}
