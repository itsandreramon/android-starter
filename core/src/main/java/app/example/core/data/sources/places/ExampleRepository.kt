package app.example.core.data.sources.places

import app.example.core.data.type.Lce
import app.example.core.domain.ExampleEntity
import app.example.core.util.fetchAndCacheMany
import app.example.core.util.fetchAndCacheSingle
import app.example.core.util.toEntity
import kotlinx.coroutines.flow.Flow

interface ExampleRepository {
    suspend fun insert(place: ExampleEntity)
    fun getById(id: Long): Flow<Lce<ExampleEntity>>
    fun getAll(): Flow<Lce<List<ExampleEntity>>>
}

class ExampleRepositoryImpl(
    private val exampleLocalDataSource: ExampleLocalDataSource,
    private val exampleRemoteDataSource: ExampleRemoteDataSource,
) : ExampleRepository {

    override suspend fun insert(place: ExampleEntity) {
        exampleLocalDataSource.insert(listOf(place))
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
