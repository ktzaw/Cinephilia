package com.ktz.cinephilia.data.source.local.room.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ktz.cinephilia.data.models.entities.movies.NowPlayingMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.NowPlayingMoviesEntity

class NowPlayignMoviesTypeConverter {

    @TypeConverter
    fun fromJsonToResults(json: String): List<NowPlayingMoviesEntity>? {
        val typeToken = object : TypeToken<List<NowPlayingMoviesEntity>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromResultsToJson(list: List<NowPlayingMoviesEntity>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonToDates(json: String): NowPlayingMoviesBaseEntity.Dates? {
        val typeToken = object : TypeToken<NowPlayingMoviesBaseEntity.Dates>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromDatesToJson(dates: NowPlayingMoviesBaseEntity.Dates?): String {
        return Gson().toJson(dates)
    }
}
