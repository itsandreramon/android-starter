package app.example.core.data.type

sealed class Lce<T> {
    class Loading<T> : Lce<T>()
    data class Content<T>(val packet: T) : Lce<T>()
    data class Error<T>(val error: Throwable) : Lce<T>()
}