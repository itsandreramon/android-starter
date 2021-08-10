package de.thb.core.data.sources.places.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import de.thb.core.data.type.Lce
import de.thb.core.domain.ExampleEntity
import de.thb.core.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Dao
interface ExampleRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(places: List<ExampleEntity>)

    @Query("SELECT * FROM examples")
    fun getAll(): Flow<List<ExampleEntity>>

    @Query("SELECT * FROM examples WHERE id = :id")
    fun getById(id: String): Flow<ExampleEntity>
}

interface ExampleLocalDataSource {
    suspend fun insert(places: List<ExampleEntity>)
    fun getAll(): Flow<Lce<List<ExampleEntity>>>
    fun getById(id: String): Flow<Lce<ExampleEntity>>
}

class ExampleLocalDataSourceImpl(
    private val applicationScope: CoroutineScope,
    private val exampleRoomDao: ExampleRoomDao,
    private val dispatcherProvider: CoroutinesDispatcherProvider,
) : ExampleLocalDataSource {

    override suspend fun insert(places: List<ExampleEntity>) {
        withContext(dispatcherProvider.database()) {
            applicationScope.launch {
                exampleRoomDao.insert(places)
            }.join()
        }
    }

    override fun getAll() = flow<Lce<List<ExampleEntity>>> {
        exampleRoomDao.getAll()
            .flowOn(dispatcherProvider.database())
            .onStart { emit(Lce.Loading()) }
            .catch { emit(Lce.Error(it)) }
            .collect { emit(Lce.Content(it)) }
    }

    override fun getById(id: String) = flow<Lce<ExampleEntity>> {
        exampleRoomDao.getById(id)
            .flowOn(dispatcherProvider.database())
            .onStart { emit(Lce.Loading()) }
            .catch { emit(Lce.Error(it)) }
            .collect { emit(Lce.Content(it)) }
    }
}
