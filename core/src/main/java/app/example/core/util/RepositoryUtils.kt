package app.example.core.util

import app.example.core.data.type.Lce
import app.example.core.data.type.Result
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException

fun <T, R> fetchAndCacheMany(
    fetch: suspend () -> Result<List<R>>,
    cache: suspend (List<T>) -> Unit,
    transformer: (R) -> T,
    getCache: () -> Flow<Lce<List<T>>>,
): Flow<Lce<List<T>>> {
    return channelFlow {
        sendCachedAsync(getCache)
        sendFetchedAsync(fetch, cache, transformer = { res ->
            res.map { transformer(it) }
        })
    }
}

fun <T, R : Any> fetchAndCacheSingle(
    fetch: suspend () -> Result<R>,
    cache: suspend (T) -> Unit,
    transformer: (R) -> T,
    getCache: () -> Flow<Lce<T>>,
): Flow<Lce<T>> {
    return channelFlow {
        sendCachedAsync(getCache)
        sendFetchedAsync(fetch, cache, transformer)
    }
}

private fun <T> ProducerScope<Lce<T>>.sendCachedAsync(
    getCache: () -> Flow<Lce<T>>
) {
    launch {
        getCache().collect {
            send(it)
        }
    }
}

private fun <T, R : Any> ProducerScope<Lce<T>>.sendFetchedAsync(
    fetch: suspend () -> Result<R>,
    cache: suspend (T) -> Unit,
    transformer: (R) -> T,
) {
    launch {
        val fetchedResult = try {
            fetch()
        } catch (e: IOException) {
            Result.Error(e)
        }

        if (fetchedResult is Result.Success) {
            cache(transformer(fetchedResult.data))
        }
    }
}