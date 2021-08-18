package app.example.core.data.sources.example

import app.example.core.data.type.Lce
import app.example.core.domain.ExampleEntity
import app.example.core.util.fetchAndCacheMany
import app.example.core.util.fetchAndCacheSingle
import app.example.core.util.toEntity
import app.example.core.util.trackInitializations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ExampleRepository {
    suspend fun insert(example: ExampleEntity)
    fun getById(id: Long): Flow<Lce<ExampleEntity>>
    fun getAll(): Flow<Lce<List<ExampleEntity>>>
}

class ExampleRepositoryImpl @Inject constructor(
    private val exampleLocalDataSource: ExampleLocalDataSource,
    private val exampleRemoteDataSource: ExampleRemoteDataSource,
) : ExampleRepository {

    init {
        trackInitializations(this)
    }

    override suspend fun insert(example: ExampleEntity) {
        exampleLocalDataSource.insert(listOf(example))
    }

    override fun getAll() = fetchAndCacheMany(
        fetch = { exampleRemoteDataSource.getAll() },
        cache = { exampleLocalDataSource.insert(it) },
        getCache = { exampleLocalDataSource.getAll() },
        transformer = { response -> response.toEntity() }
    )

    override fun getById(id: Long) = fetchAndCacheSingle(
        fetch = { exampleRemoteDataSource.getById(id) },
        cache = { exampleLocalDataSource.insert(it) },
        getCache = { exampleLocalDataSource.getById(id) },
        transformer = { response -> response.toEntity() }
    )
}
