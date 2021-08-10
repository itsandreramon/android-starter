package app.example.core.data.sources.example

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.example.core.data.type.Lce
import app.example.core.domain.BookEntity
import app.example.core.util.CoroutineDispatcherProvider
import app.example.core.util.trackInitializations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Dao
interface BookRoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(examples: List<BookEntity>)

    @Query("SELECT * FROM books")
    fun getAll(): Flow<List<BookEntity>>

    @Query("SELECT * FROM books WHERE uuid = :uuid")
    fun getByUuid(uuid: String): Flow<BookEntity>
}

interface BookLocalDataSource {
    suspend fun insert(books: List<BookEntity>)
    suspend fun insert(book: BookEntity)
    fun getAll(): Flow<Lce<List<BookEntity>>>
    fun getByUuid(uuid: String): Flow<Lce<BookEntity>>
}

class BookLocalDataSourceImpl @Inject constructor(
    private val applicationScope: CoroutineScope,
    private val bookRoomDao: BookRoomDao,
    private val dispatcherProvider: CoroutineDispatcherProvider,
) : BookLocalDataSource {

    init {
        trackInitializations(this)
    }

    override suspend fun insert(books: List<BookEntity>) {
        withContext(dispatcherProvider.database()) {
            applicationScope.launch {
                bookRoomDao.insert(books)
            }.join()
        }
    }

    override suspend fun insert(book: BookEntity) {
        insert(listOf(book))
    }

    override fun getAll() = flow<Lce<List<BookEntity>>> {
        bookRoomDao.getAll()
            .flowOn(dispatcherProvider.database())
            .onStart { emit(Lce.Loading()) }
            .catch { emit(Lce.Error(it)) }
            .collect { emit(Lce.Content(it)) }
    }

    override fun getByUuid(uuid: String) = flow<Lce<BookEntity>> {
        bookRoomDao.getByUuid(uuid)
            .flowOn(dispatcherProvider.database())
            .onStart { emit(Lce.Loading()) }
            .catch { emit(Lce.Error(it)) }
            .collect { emit(Lce.Content(it)) }
    }
}
