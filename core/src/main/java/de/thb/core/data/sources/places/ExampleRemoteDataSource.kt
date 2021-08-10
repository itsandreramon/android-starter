package de.thb.core.data.sources.places.remote

import de.thb.core.data.type.Result
import de.thb.core.domain.ExampleResponse
import de.thb.core.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException

interface ExampleService {

    @GET("/examples")
    suspend fun getAll(): List<ExampleResponse>

    @GET("/examples/{id}")
    suspend fun getById(
        @Path("id") id: Int
    ): ExampleResponse
}

interface ExampleRemoteDataSource {
    suspend fun getAll(): Result<List<ExampleResponse>>
    suspend fun getById(id: Int): Result<ExampleResponse>
}

class ExampleRemoteDataSourceImpl(
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    private val exampleService: ExampleService,
) : ExampleRemoteDataSource {

    override suspend fun getById(id: Int): Result<ExampleResponse> {
        return withContext(dispatcherProvider.io()) {
            try {
                Result.Success(exampleService.getById(id))
            } catch (e: IOException) {
                Result.Error(e)
            }
        }
    }

    override suspend fun getAll(): Result<List<ExampleResponse>> {
        return withContext(dispatcherProvider.io()) {
            try {
                Result.Success(exampleService.getAll())
            } catch (e: IOException) {
                Result.Error(e)
            }
        }
    }
}
