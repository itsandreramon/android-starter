package app.example.ui.di

import app.example.ui.screens.ExampleViewModel
import app.example.ui.util.AssistedViewModelFactory
import app.example.ui.util.MavericksViewModelComponent
import app.example.ui.util.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExampleViewModel::class)
    fun exampleViewModelFactory(factory: ExampleViewModel.Factory): AssistedViewModelFactory<*, *>
}