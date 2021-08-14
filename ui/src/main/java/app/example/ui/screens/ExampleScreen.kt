package app.example.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.example.core.data.sources.places.ExampleRepository
import app.example.core.data.type.Lce
import app.example.core.domain.ExampleEntity
import app.example.ui.components.ExampleAppBar
import app.example.ui.theme.padding_medium
import app.example.ui.util.trackRecompositions
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            placesRepository.insert(ExampleEntity(name = "Max Mustermann"))
        }

        placesRepository.getAll()
            .onEach { setState { copy(examples = it) } }
            .launchIn(viewModelScope)
    }
}

@Composable
fun ExampleScreen(viewModel: ExampleViewModel = mavericksViewModel()) {
    trackRecompositions("ExampleScreen")

    val examplesLce = viewModel.collectAsState(ExampleState::examples)

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
                            Text(example.name)
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