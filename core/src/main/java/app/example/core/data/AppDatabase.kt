package app.example.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.example.core.data.sources.example.BookRoomDao
import app.example.core.domain.BookEntity

@Database(
    version = 1,
    entities = [
        BookEntity::class,
    ],
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookRoomDao
}
