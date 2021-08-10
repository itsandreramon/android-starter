package app.example.core.hilt

import app.example.core.data.AppDatabase
import app.example.core.data.sources.example.BookRoomDao
import app.example.core.data.sources.example.BookService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleBook {

    @Provides
    @Singleton
    fun provideBookService(retrofit: Retrofit.Builder): BookService {
        return retrofit
            .build()
            .create(BookService::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRoomDao(appDatabase: AppDatabase): BookRoomDao {
        return appDatabase.bookDao()
    }
}
