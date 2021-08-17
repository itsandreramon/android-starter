package app.example.di

import android.content.Context
import app.example.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationScope(
        @ApplicationContext applicationContext: Context
    ): CoroutineScope {
        return (applicationContext as App).applicationScope
    }
}
