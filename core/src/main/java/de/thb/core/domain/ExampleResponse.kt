package de.thb.core.domain

import com.squareup.moshi.Json

data class ExampleResponse(

    @Json(name = "name")
    val name: String
)