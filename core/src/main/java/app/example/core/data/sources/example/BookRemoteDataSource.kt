package app.example.core.data.sources.example

import app.example.core.data.type.Result
import app.example.core.domain.BookResponse
import app.example.core.domain.toResponse
import app.example.core.util.CoroutineDispatcherProvider
import app.example.core.util.trackInitializations
import app.example.graphql.GetAllBooksQuery
import app.example.graphql.GetBookByUuidQuery
import app.example.graphql.InsertBookMutation
import app.example.graphql.type.BookInput
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface BookRemoteDataSource {
    suspend fun getAll(): Result<List<BookResponse>>
    suspend fun getByUuid(uuid: String): Result<BookResponse>
    suspend fun insertBook(book: BookInput): Result<BookResponse>
}

class BookRemoteDataSourceImpl @Inject constructor(
    private val dispatcherProvider: CoroutineDispatcherProvider,
    private val apollo: ApolloClient,
) : BookRemoteDataSource {

    init {
        trackInitializations(this)
    }

    override suspend fun getByUuid(uuid: String): Result<BookResponse> {
        return withContext(dispatcherProvider.io()) {
            try {
                val data = apollo.query(GetBookByUuidQuery(uuid)).execute().data!!
                Result.Success(data.toResponse())
            } catch (e: ApolloException) {
                Result.Error(e)
            }
        }
    }

    override suspend fun getAll(): Result<List<BookResponse>> {
        return withContext(dispatcherProvider.io()) {
            try {
                val data = apollo.query(GetAllBooksQuery()).execute().data!!
                Result.Success(data.toResponse())
            } catch (e: ApolloException) {
                Result.Error(e)
            }
        }
    }

    override suspend fun insertBook(book: BookInput): Result<BookResponse> {
        return withContext(dispatcherProvider.io()) {
            try {
                val data = apollo.mutation(InsertBookMutation(book)).execute().data!!
                Result.Success(data.toResponse())
            } catch (e: ApolloException) {
                Result.Error(e)
            }
        }
    }
}
