package app.example.core.di

import app.example.core.data.AppDatabase
import app.example.core.data.sources.places.ExampleLocalDataSource
import app.example.core.data.sources.places.ExampleLocalDataSourceImpl
import app.example.core.data.sources.places.ExampleRemoteDataSource
import app.example.core.data.sources.places.ExampleRemoteDataSourceImpl
import app.example.core.data.sources.places.ExampleRepository
import app.example.core.data.sources.places.ExampleRepositoryImpl
import app.example.core.data.sources.places.ExampleService
import app.example.core.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module
import retrofit2.Retrofit

val exampleModule = module {

    fun provideExampleService(retrofit: Retrofit.Builder): ExampleService {
        return retrofit
            .build()
            .create(ExampleService::class.java)
    }

    fun provideExampleRemoteDataSource(
        coroutinesDispatcherProvider: CoroutinesDispatcherProvider,
        service: ExampleService,
    ): ExampleRemoteDataSource {
        return ExampleRemoteDataSourceImpl(coroutinesDispatcherProvider, service)
    }

    fun provideExampleLocalDataSource(
        appDatabase: AppDatabase,
        dispatcherProvider: CoroutinesDispatcherProvider,
        applicationScope: CoroutineScope,
    ): ExampleLocalDataSource {
        return ExampleLocalDataSourceImpl(
            exampleRoomDao = appDatabase.exampleDao(),
            dispatcherProvider = dispatcherProvider,
            applicationScope = applicationScope,
        )
    }

    single { provideExampleService(get()) }
    single { provideExampleRemoteDataSource(get(), get()) }
    single { provideExampleLocalDataSource(get(), get(), get()) }
    single<ExampleRepository> { ExampleRepositoryImpl(get(), get()) }
}