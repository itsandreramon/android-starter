package app.example.ui.screens

import app.example.core.data.sources.example.ExampleRepository
import app.example.core.data.type.Lce
import com.airbnb.mvrx.test.MvRxTestRule
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleViewModelTest {

    @get:Rule
    val mavericksTestRule = MvRxTestRule()

    private var exampleViewModel: ExampleViewModel? = null
    private val exampleRepository: ExampleRepository = mockk()

    @Before
    fun setUp() {
        coEvery {
            exampleRepository.insert(any())
        } just Runs

        every {
            exampleRepository.getAll()
        } returns flowOf(
            Lce.Content(listOf())
        )

        exampleViewModel = ExampleViewModel(
            initialState = ExampleState(),
            exampleRepository = exampleRepository
        )
    }

    @After
    fun tearDown() {
        exampleViewModel = null
    }

    @Test
    fun example_test() {
        // your test goes here
    }
}
