package app.example.core.util

import logcat.logcat

fun trackRecompositions(name: String) {
    logcat(tag = "Recomposition") { "Recomposing $name..." }
}

fun <T> trackInitializations(instance: T) {
    logcat(tag = "Initialization") { "Initializing $instance..." }
}
