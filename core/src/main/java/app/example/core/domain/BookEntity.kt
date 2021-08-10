package app.example.core.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "books")
data class BookEntity(

    @PrimaryKey
    val uuid: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "author")
    val author: String,
)
