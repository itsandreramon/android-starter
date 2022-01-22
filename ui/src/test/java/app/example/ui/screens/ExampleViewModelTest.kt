package app.example.ui.screens

import app.cash.turbine.test
import app.example.core.data.sources.example.BookRepository
import app.example.core.data.type.Lce
import app.example.ui.screens.mvrx.MvRxTestExtension
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
    private val bookRepository: BookRepository = mockk()

    @BeforeEach
    fun setUp() {
        coEvery {
            bookRepository.insert(any())
        } just Runs

        every {
            bookRepository.getAll()
        } returns flowOf(Lce.Content(listOf()))

        exampleViewModel = ExampleViewModel(
            initialState = ExampleState(),
            bookRepository = bookRepository
        )
    }

    @AfterEach
    fun tearDown() {
        exampleViewModel = null
    }

    @Test
    fun example_test() = runTest {
        exampleViewModel!!.stateFlow.test {
            awaitItem().books shouldBe instanceOf<Lce.Loading<*>>()
            awaitItem().books shouldBe instanceOf<Lce.Content<*>>()
        }
    }

    companion object {

        @JvmField
        @RegisterExtension
        val mavericksTestExtension = MvRxTestExtension()
    }
}
