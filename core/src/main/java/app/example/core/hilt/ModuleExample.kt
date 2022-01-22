package app.example.core.hilt

import app.example.core.data.AppDatabase
import app.example.core.data.sources.example.BookRoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleExample {

    @Provides
    @Singleton
    fun provideBookRoomDao(appDatabase: AppDatabase): BookRoomDao {
        return appDatabase.bookDao()
    }
}
