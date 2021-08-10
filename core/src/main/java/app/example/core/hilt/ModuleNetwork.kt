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
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleNetwork {

    private fun getEndpoint(): String {
        return if (BuildConfig.DEBUG) {
            "http://localhost:8080/"
        } else {
            "https://example.com/"
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(getEndpoint())
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideApollo(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(getEndpoint())
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
