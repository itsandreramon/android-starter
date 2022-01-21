package app.example.ui.screens

import app.cash.turbine.test
import app.example.core.data.sources.example.ExampleRepository
import app.example.core.data.type.Lce
import app.example.core.domain.ExampleEntity
import app.example.ui.screens.mvrx.MvRxTestExtension
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class ExampleViewModelTest {

    private var exampleViewModel: ExampleViewModel? = null
    private val exampleRepository: ExampleRepository = mockk()

    @BeforeEach
    fun setUp() {
        coEvery {
            exampleRepository.insert(any())
        } just Runs

        every {
            exampleRepository.getAll()
        } returns flowOf(Lce.Content(listOf()))

        exampleViewModel = ExampleViewModel(
            initialState = ExampleState(),
            exampleRepository = exampleRepository
        )
    }

    @AfterEach
    fun tearDown() {
        exampleViewModel = null
    }

    @Test
    fun example_test() = runTest {
        exampleViewModel!!.stateFlow.test {
            awaitItem().examples shouldBe instanceOf<Lce.Loading<*>>()
            awaitItem().examples shouldBe instanceOf<Lce.Content<*>>()
        }
    }

    companion object {

        @JvmField
        @RegisterExtension
        val mavericksTestExtension = MvRxTestExtension()
    }
}
