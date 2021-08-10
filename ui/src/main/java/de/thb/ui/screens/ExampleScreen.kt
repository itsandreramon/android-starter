package de.thb.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import de.thb.core.data.sources.places.ExampleRepository
import de.thb.core.data.type.Lce
import de.thb.core.domain.ExampleEntity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class ExampleState(
    val examples: Lce<List<ExampleEntity>> = Lce.Loading()
) : MavericksState

class ExampleViewModel(
    initialState: ExampleState,
) : MavericksViewModel<ExampleState>(initialState), KoinComponent {

    // TODO use constructor injection
    private val placesRepository by inject<ExampleRepository>()

    init {
        placesRepository.getAll()
            .onEach { setState { copy(examples = it) } }
            .launchIn(viewModelScope)
    }
}

@Composable
fun ExampleScreen(viewModel: ExampleViewModel = mavericksViewModel()) {
    val examplesLce = viewModel.collectAsState(ExampleState::examples)

    when (val examples = examplesLce.value) {
        is Lce.Loading -> {
            Text("Loading...")
        }
        is Lce.Content -> {
            LazyColumn {
                items(examples.packet) { example ->
                    Text(example.name)
                }
            }
        }
        is Lce.Error -> {
            Text("An error occurred: ${examples.error.message}")
        }
    }
}