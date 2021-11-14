package com.ktz.cinephilia.data.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ktz.cinephilia.data.db.dao.FavouriteDao
import com.ktz.cinephilia.data.model.GenreIdConverter
import com.ktz.cinephilia.data.model.MovieDetail


@Database(entities = [MovieDetail::class], version = 1, exportSchema = false)
@TypeConverters(GenreIdConverter::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val favouriteDao: FavouriteDao

    companion object {
        private val lock = Any()
        private const val DB_NAME = "movie_db"
        private var INSTANCE: MoviesDatabase? = null

        fun getInstance(application: Application): MoviesDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(
                            application,
                            MoviesDatabase::class.java,
                            DB_NAME
                        )
                            .build()
                }
            }
            return INSTANCE!!
        }
    }

}