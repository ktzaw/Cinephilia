package com.ktz.cinephilia.data.source.local.room.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ktz.cinephilia.data.models.entities.movies.UpcomingMoviesBaseEntity
import com.ktz.cinephilia.data.models.entities.movies.UpcomingMoviesEntity

class UpcomingMoviesTypeConverter {

    @TypeConverter
    fun fromJsonToResults(json: String): List<UpcomingMoviesEntity>? {
        val typeToken = object : TypeToken<List<UpcomingMoviesEntity>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromResultsToJson(list: List<UpcomingMoviesEntity>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromJsonToDates(json: String): UpcomingMoviesBaseEntity.Dates? {
        val typeToken = object : TypeToken<UpcomingMoviesBaseEntity.Dates>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromDatesToJson(dates: UpcomingMoviesBaseEntity.Dates?): String {
        return Gson().toJson(dates)
    }
}
