package app.example.core.domain

import java.time.Instant
import java.util.UUID

data class BookResponse(
    val uuid: String = UUID.randomUUID().toString(),
    val title: String,
    val author: String,
    val created: String = Instant.now().toString(),
)
