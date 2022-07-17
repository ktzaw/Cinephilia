package com.ktz.cinephilia.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import timber.log.Timber

sealed class StatefulData<out T : Any> {
    data class Success<T : Any>(val result: T) : StatefulData<T>()
    data class Error(val error: String) : StatefulData<Nothing>()
    object Loading : StatefulData<Nothing>()

    inline fun <R : Any> map(transform: (T) -> R): StatefulData<R> {
        return when (this) {
            is Loading -> Loading
            is Error -> Error(this.error)
            is Success -> Success(transform(this.result))
        }
    }

    suspend inline fun <R : Any> suspendMap(crossinline transform: suspend (T) -> R): StatefulData<R> {
        return when (this) {
            is Loading -> Loading
            is Error -> Error(this.error)
            is Success -> Success(transform(this.result))
        }
    }

    fun dataOrNull(): T? {
        if (this is Success) {
            return this.result
        }
        return null
    }
}
suspend inline fun <T : StatefulData<W>, W : Any> FlowCollector<T>.wrapAsState(block: () -> Unit) = with(this) {
    try { block.invoke() } catch (exception: Exception) {
        Timber.e(exception.localizedMessage)
    }
}

fun <T : Any> stateFlow(block: suspend FlowCollector<StatefulData<T>>.() -> Unit): Flow<StatefulData<T>> = flow {
    wrapAsState { block(this) }
}


fun <T : Any> Flow<T>.asStatefulData(): Flow<StatefulData<T>> = wrapWithStatefulData()
    .catch {
        Timber.e("An error occures")
    }

fun <T : Any> Flow<T>.wrapWithStatefulData(): Flow<StatefulData<T>> = transform { value ->
    return@transform emit(StatefulData.Success(value))
}

inline fun <T : Any, R : Any> Flow<StatefulData<T>>.mapState(crossinline transform: suspend (value: T) -> R): Flow<StatefulData<R>> = transform { value ->
    return@transform emit(value.suspendMap(transform))
}

inline fun <T : Any, R : Any> Flow<StatefulData<List<T>>>.mapStateList(crossinline transform: suspend (value: T) -> R): Flow<StatefulData<List<R>>> = transform { value ->
    return@transform emit(
        value.suspendMap {
            it.map { item -> transform(item) }
        }
    )
}

inline fun <T : Any, R : Any> Flow<StatefulData<List<T>>>.mapStateListIndexed(crossinline transform: suspend (index: Int, value: T) -> R): Flow<StatefulData<List<R>>> = transform { value ->
    return@transform emit(
        value.suspendMap {
            it.mapIndexed { index, item -> transform(index, item) }
        }
    )
}

inline fun <T : Any, R : Any> Flow<StatefulData<T>>.mapStateData(crossinline transform: suspend (value: T) -> R): Flow<R> = transform { value ->
    if (value is StatefulData.Success) {
        return@transform emit(transform(value.result))
    }
}

inline fun <T : Any> Flow<StatefulData<T>>.onSuccessState(crossinline action: suspend (value: T) -> Unit): Flow<StatefulData<T>> = onEach {
    if (it is StatefulData.Success) {
        action(it.result)
    }
}

inline fun <T : Any> Flow<StatefulData<T>>.onErrorState(crossinline action: suspend (error: Exception) -> Unit): Flow<StatefulData<T>> = onEach {
    if (it is StatefulData.Error) {
        Timber.e("An error occures")
    }
}

inline fun <T : Any> Flow<StatefulData<T>>.onLoadingState(crossinline action: suspend () -> Unit): Flow<StatefulData<T>> = onEach {
    if (it is StatefulData.Loading) {
        action()
    }
}

inline fun <T : Any, R : Any> StatefulData<List<T>>.mapData(transform: (T) -> R): List<R> {
    return when (this) {
        is StatefulData.Success -> this.result.map { transform(it) }
        else -> emptyList()
    }
}
