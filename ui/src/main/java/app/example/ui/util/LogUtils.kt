package app.example.ui.util

import timber.log.Timber

fun trackRecompositions(name: String) {
    Timber.d("Recomposing $name")
}