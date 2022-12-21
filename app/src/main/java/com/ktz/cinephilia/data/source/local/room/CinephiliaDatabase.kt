package com.ktz.cinephilia.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ktz.cinephilia.data.models.entities.movies.NowPlayingMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.NowPlayingMoviesEntity
import com.ktz.cinephilia.data.models.entities.movies.PopularMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.PopularMoviesEntity
import com.ktz.cinephilia.data.models.entities.movies.TopRatedMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.TopRatedMoviesEntity
import com.ktz.cinephilia.data.models.entities.movies.UpcomingMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.UpcomingMoviesEntity
import com.ktz.cinephilia.data.models.entities.moviesList.NowPlayingMoviesListEntity
import com.ktz.cinephilia.data.models.entities.moviesList.PopularMoviesListEntity
import com.ktz.cinephilia.data.models.entities.moviesList.UpcomingMoviesListEntity
import com.ktz.cinephilia.data.models.entities.remoteKeys.NowPlayingMoviesRemoteKeys
import com.ktz.cinephilia.data.models.entities.remoteKeys.PopularMoviesRemoteKeys
import com.ktz.cinephilia.data.models.entities.remoteKeys.UpcomingMoviesRemoteKeys
import com.ktz.cinephilia.data.models.entities.tv.OnAirEntity
import com.ktz.cinephilia.data.models.entities.tv.PopularTvEntity
import com.ktz.cinephilia.data.models.entities.tv.TopRatedTvEntity
import com.ktz.cinephilia.data.source.local.room.daos.movies.NowPlayingMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.PopularMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.TopRatedMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.movies.UpcomingMoviesDao
import com.ktz.cinephilia.data.source.local.room.daos.moviesList.NowPlayingMoviesListDao
import com.ktz.cinephilia.data.source.local.room.daos.moviesList.PopularMoviesListDao
import com.ktz.cinephilia.data.source.local.room.daos.moviesList.UpcomingMoviesListDao
import com.ktz.cinephilia.data.source.local.room.daos.remoteKeysDao.NowPlayingMoviesListRemoteKeysDao
import com.ktz.cinephilia.data.source.local.room.daos.remoteKeysDao.PopularMoviesListRemoteKeysDao
import com.ktz.cinephilia.data.source.local.room.daos.remoteKeysDao.UpcomingMoviesListRemoteKeysDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.OnAirTvDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.PopularTvDao
import com.ktz.cinephilia.data.source.local.room.daos.tv.TopRatedTvDao
import com.ktz.cinephilia.data.source.local.room.typeConverters.CountryListTypeConverter
import com.ktz.cinephilia.data.source.local.room.typeConverters.GenreIdTypeConverter
import com.ktz.cinephilia.data.source.local.room.typeConverters.NowPlayignMoviesTypeConverter
import com.ktz.cinephilia.data.source.local.room.typeConverters.PopularMoviesTypeConverter
import com.ktz.cinephilia.data.source.local.room.typeConverters.TopRatedMoviesTypeConverter
import com.ktz.cinephilia.data.source.local.room.typeConverters.UpcomingMoviesTypeConverter

@Database(
    version = 1,
    entities = [
        TopRatedMoviesBaseEntity::class,
        TopRatedMoviesEntity::class,
        NowPlayingMoviesBaseEntity::class,
        NowPlayingMoviesEntity::class,
        PopularMoviesBaseEntity::class,
        PopularMoviesEntity::class,
        UpcomingMoviesBaseEntity::class,
        UpcomingMoviesEntity::class,
        TopRatedTvEntity::class,
        OnAirEntity::class,
        PopularTvEntity::class,
        NowPlayingMoviesListEntity::class,
        NowPlayingMoviesRemoteKeys::class,
        PopularMoviesListEntity::class,
        PopularMoviesRemoteKeys::class,
        UpcomingMoviesListEntity::class,
        UpcomingMoviesRemoteKeys::class
    ],
    exportSchema = false
)
@TypeConverters(
    GenreIdTypeConverter::class,
    CountryListTypeConverter::class,
    TopRatedMoviesTypeConverter::class,
    NowPlayignMoviesTypeConverter::class,
    PopularMoviesTypeConverter::class,
    UpcomingMoviesTypeConverter::class
)
abstract class CinephiliaDatabase : RoomDatabase() {

    companion object {
        private val lock = Any()
        private const val DB_NAME = "Cinephilia.db"
        private var INSTANCE: CinephiliaDatabase? = null

        fun getInstance(context: Context): CinephiliaDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CinephiliaDatabase::class.java,
                        DB_NAME
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun topRatedMoviesDao(): TopRatedMoviesDao
    abstract fun nowPlayingMoviesDao(): NowPlayingMoviesDao
    abstract fun popularMoviesDao(): PopularMoviesDao
    abstract fun upcomingMoviesDao(): UpcomingMoviesDao

    abstract fun topRatedTvDao(): TopRatedTvDao
    abstract fun onAirTvDao(): OnAirTvDao
    abstract fun popularTvDao(): PopularTvDao

    abstract fun nowPlayingMoviesListDao(): NowPlayingMoviesListDao
    abstract fun nowPlayingMoviesListRemoteKeysDao(): NowPlayingMoviesListRemoteKeysDao

    abstract fun popularMoviesListDao(): PopularMoviesListDao
    abstract fun popularMoviesRemoteKeysDao(): PopularMoviesListRemoteKeysDao

    abstract fun upcomingMoviesListDao(): UpcomingMoviesListDao
    abstract fun upcomingMoviesRemoteKeysDao(): UpcomingMoviesListRemoteKeysDao
}
