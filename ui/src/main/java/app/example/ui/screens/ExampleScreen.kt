package app.example.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.example.core.data.sources.example.BookRepository
import app.example.core.data.type.Lce
import app.example.core.domain.BookEntity
import app.example.core.util.trackInitializations
import app.example.core.util.trackRecompositions
import app.example.ui.components.ExampleAppBar
import app.example.ui.theme.padding_medium
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

data class ExampleState(
    val books: Lce<List<BookEntity>> = Lce.Loading(),
) : MavericksState

class ExampleViewModel @AssistedInject constructor(
    @Assisted initialState: ExampleState,
    private val bookRepository: BookRepository,
) : MavericksViewModel<ExampleState>(initialState) {

    init {
        trackInitializations(this)

        viewModelScope.launch {
            bookRepository.insert(
                book = BookEntity(
                    uuid = "123-abc",
                    title = "Harry Potter and the Philosopher's Stone",
                    author = "J. K. Rowling",
                ),
            )
        }

        bookRepository.getAll()
            .onEach { delay(500) }
            .onEach { setState { copy(books = it) } }
            .launchIn(viewModelScope)
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ExampleViewModel, ExampleState> {
        override fun create(state: ExampleState): ExampleViewModel
    }

    companion object : MavericksViewModelFactory<ExampleViewModel, ExampleState> by hiltMavericksViewModelFactory()
}

@Composable
fun ExampleScreen(viewModel: ExampleViewModel = mavericksViewModel()) {
    trackRecompositions("ExampleScreen")

    val examplesLce = viewModel.collectAsState(ExampleState::books)

    Column {
        ExampleAppBar(title = "Examples")

        Box(Modifier.padding(padding_medium)) {
            when (val examples = examplesLce.value) {
                is Lce.Loading -> {
                    Text("Loading...")
                }
                is Lce.Content -> {
                    LazyColumn {
                        items(examples.packet) { example ->
                            Text(example.title)
                        }
                    }
                }
                is Lce.Error -> {
                    Text("An error occurred: ${examples.error.message}")
                }
            }
        }
    }
}
