package app.example.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import app.example.core.data.sources.places.ExampleRoomDao
import app.example.core.domain.ExampleEntity

@Database(
    version = 1,
    entities = [
        ExampleEntity::class
    ],
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleRoomDao
}
