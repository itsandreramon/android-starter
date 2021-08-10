package app.example.ui.screens

import app.example.core.util.CoroutineDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Add this JUnit 5 extension to your test class using:
 *
 * @JvmField
 * @RegisterExtension
 * val coroutineTestExtension = CoroutinesTestExtension()
 */
class CoroutineTestExtension(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler()),
) : BeforeEachCallback, AfterEachCallback {

    val testDispatcherProvider = object : CoroutineDispatcherProvider {
        override fun main(): CoroutineDispatcher = testDispatcher
        override fun computation(): CoroutineDispatcher = testDispatcher
        override fun io(): CoroutineDispatcher = testDispatcher
        override fun database(): CoroutineDispatcher = testDispatcher
    }

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}
