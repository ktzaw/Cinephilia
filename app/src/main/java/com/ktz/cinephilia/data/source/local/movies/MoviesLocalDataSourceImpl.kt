package com.ktz.cinephilia.data.source.local.movies

import com.ktz.cinephilia.data.models.entities.movies.toNowPlayingMoviesResponse
import com.ktz.cinephilia.data.models.entities.movies.toPopularMoviesResponse
import com.ktz.cinephilia.data.models.entities.movies.toTopRatedMoviesResponse
import com.ktz.cinephilia.data.models.entities.movies.toUpcomingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.NowPlayingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.PopularMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.TopRatedMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.UpcomingMoviesResponse
import com.ktz.cinephilia.data.models.remote.movies.toNowPlayingMoviesEntity
import com.ktz.cinephilia.data.models.remote.movies.toPopularMoviesEntity
import com.ktz.cinephilia.data.models.remote.movies.toTopRatedMoviesEntity
import com.ktz.cinephilia.data.models.remote.movies.toUpcomingMoviesEntity
import com.ktz.cinephilia.data.source.local.room.daos.movies.NowPlayingMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.PopularMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.TopRatedMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.UpcomingMoviesDao
import com.ktz.cinephilia.utils.StatefulData
import com.ktz.cinephilia.utils.asStatefulData
import com.ktz.cinephilia.utils.mapList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesLocalDataSourceImpl @Inject constructor(
    private val topRatedMoviesDao: TopRatedMoviesDao,
    private val nowPlayingMoviesDao: NowPlayingMoviesDao,
    private val popularMoviesDao: PopularMoviesDao,
    private val upcomingMoviesDao: UpcomingMoviesDao
) : MoviesLocalDataSource {

    override fun saveAllTopRatedMovies(movies: List<TopRatedMoviesResponse>) {
        topRatedMoviesDao.insertTopRatedMovies(
            movies.map {
                it.toTopRatedMoviesEntity()
            }
        )
    }

    override fun saveAllNowPlayingMovies(movies: List<NowPlayingMoviesResponse>) {
        nowPlayingMoviesDao.insertNowPlayingMovies(
            movies.map {
                it.toNowPlayingMoviesEntity()
            }
        )
    }

    override fun saveAllPopularMovies(movies: List<PopularMoviesResponse>) {
        popularMoviesDao.insertPopularMovies(
            movies.map {
                it.toPopularMoviesEntity()
            }
        )
    }

    override fun saveAllUpcomingMovies(movies: List<UpcomingMoviesResponse>) {
        upcomingMoviesDao.insertUpcomingMovies(
            movies.map {
                it.toUpcomingMoviesEntity()
            }
        )
    }

    override fun getAllTopRatedMovies(): Flow<StatefulData<List<TopRatedMoviesResponse>>> =
        topRatedMoviesDao.getAllTopRatedMovies().mapList {
            it.toTopRatedMoviesResponse()
        }
            .asStatefulData()

    override fun getAllNowPlayingMovies(): Flow<StatefulData<List<NowPlayingMoviesResponse>>> =
        nowPlayingMoviesDao.getAllNowPlayingMovies().mapList {
            it.toNowPlayingMoviesResponse()
        }
            .asStatefulData()

    override fun getAllPopularMovies(): Flow<StatefulData<List<PopularMoviesResponse>>> =
        popularMoviesDao.getAllPopularMovies().mapList {
            it.toPopularMoviesResponse()
        }
            .asStatefulData()

    override fun getAllUpcomingMovies(): Flow<StatefulData<List<UpcomingMoviesResponse>>> =
        upcomingMoviesDao.getAllUpcomingMovies().mapList {
            it.toUpcomingMoviesResponse()
        }
            .asStatefulData()

    override fun getTopRatedMovieById(id: Int): Flow<StatefulData<TopRatedMoviesResponse>> =
        topRatedMoviesDao.getTopRatedMovieById(id).map {
            it.toTopRatedMoviesResponse()
        }.asStatefulData()

    override fun getNowPlayingMovieById(id: Int): Flow<StatefulData<NowPlayingMoviesResponse>> =
        nowPlayingMoviesDao.getNowPlayingMovieById(id).map {
            it.toNowPlayingMoviesResponse()
        }.asStatefulData()

    override fun getPopularMovieById(id: Int): Flow<StatefulData<PopularMoviesResponse>> = popularMoviesDao.getPopularMovieById(id).map {
        it.toPopularMoviesResponse()
    }.asStatefulData()

    override fun getUpcomingMovieById(id: Int): Flow<StatefulData<UpcomingMoviesResponse>> =
        upcomingMoviesDao.getUpcomingMovieById(id).map {
            it.toUpcomingMoviesResponse()
        }.asStatefulData()

    override fun deleteTopRatedMovies() {
        topRatedMoviesDao.deleteAllTopRatedMovies()
    }

    override fun deleteNowPlayingMovies() {
        nowPlayingMoviesDao.deleteAllNowPlayingMovies()
    }

    override fun deletePopularMovies() {
        popularMoviesDao.deleteAllPopularMovies()
    }

    override fun deleteUpcomingMovies() {
        upcomingMoviesDao.deleteAllUpcomingMovies()
    }
}
