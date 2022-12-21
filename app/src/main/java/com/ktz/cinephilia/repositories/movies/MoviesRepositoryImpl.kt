package com.ktz.cinephilia.repositories.movies

import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesResponse
import com.ktz.cinephilia.data.source.local.movies.MoviesLocalDataSource
import com.ktz.cinephilia.data.source.remote.movies.MoviesRemoteDataSource
import com.ktz.cinephilia.utils.StatefulData
import com.ktz.cinephilia.utils.onSuccessState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@FlowPreview
class MoviesRepositoryImpl @Inject constructor(
    private val localDataSource: MoviesLocalDataSource,
    private val remoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {

    override fun getTopRatedMovies(page: Int): Flow<StatefulData<List<TopRatedMoviesResponse>>> =
        remoteDataSource.getTopRatedMovies(page)
            .onSuccessState { localDataSource.saveAllTopRatedMovies(it) }
            .flatMapMerge { localDataSource.getAllTopRatedMovies() }
            .onStart { StatefulData.Loading }
            .flowOn(Dispatchers.IO)

    override fun getNowPlayingMovies(page: Int): Flow<StatefulData<List<NowPlayingMoviesResponse>>> =
        remoteDataSource.getNowPlayingMovies(page)
            .onSuccessState { localDataSource.saveAllNowPlayingMovies(it) }
            .flatMapMerge { localDataSource.getAllNowPlayingMovies() }
            .onStart { StatefulData.Loading }
            .flowOn(Dispatchers.IO)

    override fun getPopularMovies(page: Int): Flow<StatefulData<List<PopularMoviesResponse>>> =
        remoteDataSource.getPopularMovies(page)
            .onSuccessState { localDataSource.saveAllPopularMovies(it) }
            .flatMapMerge { localDataSource.getAllPopularMovies() }
            .onStart { StatefulData.Loading }
            .flowOn(Dispatchers.IO)

    override fun getUpcomingMovies(page: Int): Flow<StatefulData<List<UpcomingMoviesResponse>>> =
        remoteDataSource.getUpcomingMovies(page)
            .onSuccessState { localDataSource.saveAllUpcomingMovies(it) }
            .flatMapMerge { localDataSource.getAllUpcomingMovies() }
            .onStart { StatefulData.Loading }
            .flowOn(Dispatchers.IO)
}
