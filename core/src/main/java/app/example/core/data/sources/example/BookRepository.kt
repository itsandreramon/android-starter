package app.example.core.data.sources.example

import app.example.core.data.type.Lce
import app.example.core.domain.BookEntity
import app.example.core.domain.toEntity
import app.example.core.util.fetchAndCacheMany
import app.example.core.util.fetchAndCacheSingle
import app.example.core.util.trackInitializations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface BookRepository {
    suspend fun insert(book: BookEntity)
    fun getByUuid(uuid: String): Flow<Lce<BookEntity>>
    fun getAll(): Flow<Lce<List<BookEntity>>>
}

class BookRepositoryImpl @Inject constructor(
    private val bookLocalDataSource: BookLocalDataSource,
    private val bookRemoteDataSource: BookRemoteDataSource,
) : BookRepository {

    init {
        trackInitializations(this)
    }

    override suspend fun insert(book: BookEntity) {
        bookLocalDataSource.insert(listOf(book))
    }

    override fun getAll() = fetchAndCacheMany(
        fetch = { bookRemoteDataSource.getAll() },
        cache = { bookLocalDataSource.insert(it) },
        getCache = { bookLocalDataSource.getAll() },
        transformer = { response -> response.toEntity() },
    )

    override fun getByUuid(uuid: String) = fetchAndCacheSingle(
        fetch = { bookRemoteDataSource.getByUuid(uuid) },
        cache = { bookLocalDataSource.insert(it) },
        getCache = { bookLocalDataSource.getByUuid(uuid) },
        transformer = { response -> response.toEntity() },
    )
}
