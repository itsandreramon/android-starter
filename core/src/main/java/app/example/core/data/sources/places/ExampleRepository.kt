package app.example.core.data.sources.places

import app.example.core.data.type.Lce
import app.example.core.domain.ExampleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

interface ExampleRepository {
    suspend fun insert(place: ExampleEntity)
    fun getById(id: String): Flow<Lce<ExampleEntity>>
    fun getAll(): Flow<Lce<List<ExampleEntity>>>
}

class ExampleRepositoryImpl(
    private val exampleLocalDataSource: ExampleLocalDataSource,
    private val exampleRemoteDataSource: ExampleRemoteDataSource,
) : ExampleRepository {

    override suspend fun insert(place: ExampleEntity) {
        exampleLocalDataSource.insert(listOf(place))
    }

    override fun getAll() = flow {
        // TODO fetch remote
        exampleLocalDataSource.getAll()
            .collect { emit(it) }
    }

    override fun getById(id: String) = flow {
        // TODO fetch remote
        exampleLocalDataSource.getById(id)
            .collect { emit(it) }
    }
}
