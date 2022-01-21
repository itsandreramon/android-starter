package app.example.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.example.core.data.sources.example.ExampleRepository
import app.example.core.data.type.Lce
import app.example.core.domain.ExampleEntity
import app.example.core.util.trackInitializations
import app.example.core.util.trackRecompositions
import app.example.ui.components.ExampleAppBar
import app.example.ui.theme.padding_medium
import app.example.ui.util.AssistedViewModelFactory
import app.example.ui.util.hiltMavericksViewModelFactory
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

data class ExampleState(
    val examples: Lce<List<ExampleEntity>> = Lce.Loading(),
) : MavericksState

class ExampleViewModel @AssistedInject constructor(
    @Assisted initialState: ExampleState,
    private val exampleRepository: ExampleRepository,
) : MavericksViewModel<ExampleState>(initialState) {

    init {
        trackInitializations(this)

        viewModelScope.launch {
            exampleRepository.insert(ExampleEntity(name = "Example"))
        }

        exampleRepository.getAll()
            .onEach { delay(500) }
            .onEach { setState { copy(examples = it) } }
            .launchIn(viewModelScope)
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ExampleViewModel, ExampleState> {
        override fun create(state: ExampleState): ExampleViewModel
    }

    companion object : MavericksViewModelFactory<ExampleViewModel, ExampleState>
    by hiltMavericksViewModelFactory()
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
