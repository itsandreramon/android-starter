package app.example.core.hilt

import app.example.core.BuildConfig
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleNetwork {

    @Provides
    @Singleton
    fun provideApollo(okHttpClient: OkHttpClient): ApolloClient {
        val endpoint = if (BuildConfig.DEBUG) {
            "http://localhost:8080/graphql"
        } else {
            "https://example.com/graphql"
        }

        return ApolloClient.Builder()
            .serverUrl(endpoint)
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }
}
