package app.example.core.data.sources.example

import app.example.core.data.type.Result
import app.example.core.domain.ExampleResponse
import app.example.core.util.CoroutineDispatcherProvider
import app.example.core.util.trackInitializations
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface ExampleService {

    @GET("/examples")
    suspend fun getAll(): List<ExampleResponse>

    @GET("/examples/{id}")
    suspend fun getById(
        @Path("id") id: Long
    ): ExampleResponse
}

interface ExampleRemoteDataSource {
    suspend fun getAll(): Result<List<ExampleResponse>>
    suspend fun getById(id: Long): Result<ExampleResponse>
}

class ExampleRemoteDataSourceImpl @Inject constructor(
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val exampleService: ExampleService,
) : ExampleRemoteDataSource {

    init {
        trackInitializations(this)
    }

    override suspend fun getById(id: Long): Result<ExampleResponse> {
        return withContext(dispatcherProvider.io()) {
            try {
                Result.Success(exampleService.getById(id))
            } catch (e: HttpException) {
                Result.Error(e)
            }
        }
    }

    override suspend fun getAll(): Result<List<ExampleResponse>> {
        return withContext(dispatcherProvider.io()) {
            try {
                Result.Success(exampleService.getAll())
            } catch (e: HttpException) {
                Result.Error(e)
            }
        }
    }
}
