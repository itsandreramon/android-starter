package de.thb.core.di

import de.thb.core.data.AppDatabase
import de.thb.core.data.sources.places.ExampleRepository
import de.thb.core.data.sources.places.ExampleRepositoryImpl
import de.thb.core.data.sources.places.local.ExampleLocalDataSource
import de.thb.core.data.sources.places.local.ExampleLocalDataSourceImpl
import de.thb.core.data.sources.places.remote.ExampleRemoteDataSource
import de.thb.core.data.sources.places.remote.ExampleRemoteDataSourceImpl
import de.thb.core.data.sources.places.remote.ExampleService
import de.thb.core.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module
import retrofit2.Retrofit

val exampleModule = module {
    fun providePlacesService(retrofit: Retrofit.Builder): ExampleService {
        return retrofit
            .build()
            .create(ExampleService::class.java)
    }

    fun providePlacesRemoteDataSource(
        coroutinesDispatcherProvider: CoroutinesDispatcherProvider,
        service: ExampleService,
    ): ExampleRemoteDataSource {
        return ExampleRemoteDataSourceImpl(coroutinesDispatcherProvider, service)
    }

    fun providePlacesLocalDataSource(
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

    single { providePlacesService(get()) }
    single { providePlacesRemoteDataSource(get(), get()) }
    single { providePlacesLocalDataSource(get(), get(), get()) }
    single<ExampleRepository> { ExampleRepositoryImpl(get(), get()) }
}