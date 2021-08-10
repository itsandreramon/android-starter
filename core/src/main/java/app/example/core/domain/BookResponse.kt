package app.example.core.domain

import com.squareup.moshi.Json
import java.util.UUID

data class BookResponse(

    @Json(name = "uuid")
    val uuid: String = UUID.randomUUID().toString(),

    @Json(name = "title")
    val title: String,

    @Json(name = "author")
    val author: String,
)
