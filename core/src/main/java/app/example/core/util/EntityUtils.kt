package app.example.core.util

import app.example.core.domain.ExampleEntity
import app.example.core.domain.ExampleResponse

fun ExampleResponse.toEntity(): ExampleEntity {
    return ExampleEntity(
        id = this.id,
        name = this.name,
    )
}