package app.example.core.data.sources.example

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.example.core.data.type.Lce
import app.example.core.domain.ExampleEntity
import app.example.core.util.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Dao
interface ExampleRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(examples: List<ExampleEntity>)

    @Query("SELECT * FROM examples")
    fun getAll(): Flow<List<ExampleEntity>>

    @Query("SELECT * FROM examples WHERE id = :id")
    fun getById(id: Long): Flow<ExampleEntity>
}

interface ExampleLocalDataSource {
    suspend fun insert(examples: List<ExampleEntity>)
    suspend fun insert(example: ExampleEntity)
    fun getAll(): Flow<Lce<List<ExampleEntity>>>
    fun getById(id: Long): Flow<Lce<ExampleEntity>>
}

class ExampleLocalDataSourceImpl @Inject constructor(
    private val applicationScope: CoroutineScope,
    private val exampleRoomDao: ExampleRoomDao,
    private val dispatcherProvider: CoroutinesDispatcherProvider,
) : ExampleLocalDataSource {

    override suspend fun insert(examples: List<ExampleEntity>) {
        withContext(dispatcherProvider.database()) {
            applicationScope.launch {
                exampleRoomDao.insert(examples)
            }.join()
        }
    }

    override suspend fun insert(example: ExampleEntity) {
        insert(listOf(example))
    }

    override fun getAll() = flow<Lce<List<ExampleEntity>>> {
        exampleRoomDao.getAll()
            .flowOn(dispatcherProvider.database())
            .onStart { emit(Lce.Loading()) }
            .catch { emit(Lce.Error(it)) }
            .collect { emit(Lce.Content(it)) }
    }

    override fun getById(id: Long) = flow<Lce<ExampleEntity>> {
        exampleRoomDao.getById(id)
            .flowOn(dispatcherProvider.database())
            .onStart { emit(Lce.Loading()) }
            .catch { emit(Lce.Error(it)) }
            .collect { emit(Lce.Content(it)) }
    }
}
