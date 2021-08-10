package de.thb.core

import de.thb.core.domain.place.PlaceEntity
import de.thb.core.domain.place.PlaceResponse
import de.thb.core.util.PlaceUtils.toEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class EntityUtilsTest {

    @Test
    fun place_response_to_entity() {
        val expected = PlaceEntity(
            id = "BAR",
            name = "Barnim",
            type = "landkreis",
            incidence = 0.0,
            trend = -1,
            website = "null",
            example = false,
        )

        val actual = PlaceResponse(
            id = "BAR",
            name = "Barnim",
            type = "landkreis",
            incidence = 0.0,
            trend = -1,
            website = "null",
            example = false
        ).toEntity()

        assertEquals(expected, actual)
    }
}
