package app.example.core.di

import android.content.Context
import androidx.room.Room
import app.example.core.data.AppDatabase
import app.example.core.util.CoroutinesDispatcherProvider
import app.example.core.util.DefaultDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDispatcherProvider(): CoroutinesDispatcherProvider {
        return DefaultDispatcherProvider()
    }
}
