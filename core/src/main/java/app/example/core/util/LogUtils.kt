package app.example.core.util

import timber.log.Timber

fun trackRecompositions(name: String) {
    Timber.d("Recomposing $name")
}

fun <T> trackInitializations(instance: T) {
    Timber.d("Initializing $instance")
}
