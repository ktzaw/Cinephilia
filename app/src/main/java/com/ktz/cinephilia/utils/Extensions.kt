package com.ktz.cinephilia.utils

import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesBaseResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

inline fun <T, R> Flow<List<T>>.mapList(crossinline transform: suspend (value: T) -> R): Flow<List<R>> = transform { value ->
    return@transform emit(value.map { transform(it) })
}

inline fun <reified T : Any> T.toStateful(): StatefulData<T> =
    StatefulData.Success(this)

fun TopRatedMoviesBaseResponse.toTopRatedStatefulList(): StatefulData<List<TopRatedMoviesResponse>> =
    StatefulData.Success(this.results)
