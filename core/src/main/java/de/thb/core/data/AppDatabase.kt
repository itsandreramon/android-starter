package de.thb.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import de.thb.core.data.sources.places.local.ExampleRoomDao
import de.thb.core.domain.ExampleEntity

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
