package app.example.ui.screens

import app.cash.turbine.test
import app.example.core.data.sources.example.ExampleRepository
import app.example.core.data.type.Lce
import com.airbnb.mvrx.test.MvRxTestRule
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleViewModelTest {

    @get:Rule
    val mavericksTestRule = MvRxTestRule()

    @get:Rule
    val coroutinesTestRule = CoroutineTestRule()

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
    fun example_test() = coroutinesTestRule.testDispatcher.runBlockingTest {
        exampleViewModel!!.stateFlow.test {
            awaitItem().examples shouldBeEqualToComparingFields Lce.Loading()
            awaitItem().examples shouldBeEqualToComparingFields Lce.Content(listOf())
        }
    }
}
