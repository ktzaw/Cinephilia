package com.ktz.cinephilia.data.source.local.room.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ktz.cinephilia.data.models.entities.movies.PopularMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.PopularMoviesEntity

class PopularMoviesTypeConverter {

    @TypeConverter
    fun fromJsonToResults(json: String): List<PopularMoviesEntity>? {
        val typeToken = object : TypeToken<List<PopularMoviesEntity>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromResultsToJson(list: List<PopularMoviesEntity>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonToDates(json: String): PopularMoviesBaseEntity.Dates? {
        val typeToken = object : TypeToken<PopularMoviesBaseEntity.Dates>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromDatesToJson(dates: PopularMoviesBaseEntity.Dates?): String {
        return Gson().toJson(dates)
    }
}
