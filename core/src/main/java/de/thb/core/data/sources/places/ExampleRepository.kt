package de.thb.core.data.sources.places

import de.thb.core.data.sources.places.local.ExampleLocalDataSource
import de.thb.core.data.sources.places.remote.ExampleRemoteDataSource
import de.thb.core.data.type.Lce
import de.thb.core.domain.ExampleEntity
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
