package app.example.core.di

import app.example.core.data.sources.example.ExampleLocalDataSource
import app.example.core.data.sources.example.ExampleLocalDataSourceImpl
import app.example.core.data.sources.example.ExampleRemoteDataSource
import app.example.core.data.sources.example.ExampleRemoteDataSourceImpl
import app.example.core.data.sources.example.ExampleRepository
import app.example.core.data.sources.example.ExampleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingExample {

    @Binds
    @Singleton
    abstract fun bindExampleRepository(
        exampleRepository: ExampleRepositoryImpl
    ): ExampleRepository

    @Binds
    @Singleton
    abstract fun bindExampleLocalDataSource(
        exampleLocalDataSource: ExampleLocalDataSourceImpl
    ): ExampleLocalDataSource

    @Binds
    @Singleton
    abstract fun bindExampleRemoteDataSource(
        exampleRemoteDataSource: ExampleRemoteDataSourceImpl
    ): ExampleRemoteDataSource
}
