package app.example.ui.hilt

import app.example.ui.screens.ExampleViewModel
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ModuleViewModel {

    @Binds
    @IntoMap
    @ViewModelKey(ExampleViewModel::class)
    fun exampleViewModelFactory(factory: ExampleViewModel.Factory): AssistedViewModelFactory<*, *>
}
