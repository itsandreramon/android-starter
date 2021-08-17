package app.example.core.di

import app.example.core.data.AppDatabase
import app.example.core.data.sources.example.ExampleRoomDao
import app.example.core.data.sources.example.ExampleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleExample {

    @Provides
    @Singleton
    fun provideExampleService(retrofit: Retrofit.Builder): ExampleService {
        return retrofit
            .build()
            .create(ExampleService::class.java)
    }

    @Provides
    @Singleton
    fun provideExampleRoomDao(appDatabase: AppDatabase): ExampleRoomDao {
        return appDatabase.exampleDao()
    }
}
