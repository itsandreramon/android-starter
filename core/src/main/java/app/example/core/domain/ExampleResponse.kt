package app.example.core.domain

import com.squareup.moshi.Json

data class ExampleResponse(

    @Json(name = "id")
    val id: Long,

    @Json(name = "name")
    val name: String
)
