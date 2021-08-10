package app.example.ui.screens

import app.cash.turbine.test
import app.example.core.data.sources.example.BookRepository
import app.example.core.data.type.Lce
import app.example.core.domain.BookEntity
import com.airbnb.mvrx.test.MavericksTestExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.instanceOf
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

class ExampleViewModelTest {

    private var exampleViewModel: ExampleViewModel? = null
    private val bookRepository: BookRepository = mockk()

    private val books = listOf(
        BookEntity(
            uuid = "123-abc",
            title = "Harry Potter and the Philosopher's Stone",
            author = "J. K. Rowling",
        ),
    )

    @BeforeEach
    fun setUp() {
        coEvery {
            bookRepository.insert(any())
        } just Runs

        every {
            bookRepository.getAll()
        } returns flowOf(Lce.Content(books))

        exampleViewModel = ExampleViewModel(
            initialState = ExampleState(),
            bookRepository = bookRepository,
        )
    }

    @AfterEach
    fun tearDown() {
        exampleViewModel = null
    }

    @Test
    fun test_initial_state() = runTest {
        exampleViewModel!!.stateFlow.test {
            awaitItem().books shouldBe instanceOf<Lce.Loading<*>>()
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun test_update_state() = runTest {
        exampleViewModel!!.stateFlow.drop(1).test {
            (awaitItem().books as Lce.Content<List<BookEntity>>).packet shouldBe books
        }

        coVerify {
            val book = books.first { it.uuid == "123-abc" }
            bookRepository.insert(book)
        }
    }

    companion object {

        @JvmField
        @RegisterExtension
        val mavericksTestExtension = MavericksTestExtension()
    }
}
