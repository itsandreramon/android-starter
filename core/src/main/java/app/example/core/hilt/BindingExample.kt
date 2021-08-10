package app.example.core.hilt

import app.example.core.data.sources.example.BookLocalDataSource
import app.example.core.data.sources.example.BookLocalDataSourceImpl
import app.example.core.data.sources.example.BookRemoteDataSource
import app.example.core.data.sources.example.BookRemoteDataSourceImpl
import app.example.core.data.sources.example.BookRepository
import app.example.core.data.sources.example.BookRepositoryImpl
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
    abstract fun bindBookRepository(
        exampleRepository: BookRepositoryImpl,
    ): BookRepository

    @Binds
    @Singleton
    abstract fun bindBookLocalDataSource(
        exampleLocalDataSource: BookLocalDataSourceImpl,
    ): BookLocalDataSource

    @Binds
    @Singleton
    abstract fun bindBookRemoteDataSource(
        exampleRemoteDataSource: BookRemoteDataSourceImpl,
    ): BookRemoteDataSource
}
