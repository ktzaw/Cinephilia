package com.ktz.cinephilia.data.source.local.room.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ktz.cinephilia.data.models.entities.movies.TopRatedMoviesEntity

class TopRatedMoviesTypeConverter {

    @TypeConverter
    fun fromJsonToResults(json: String): List<TopRatedMoviesEntity>? {
        val typeToken = object : TypeToken<List<TopRatedMoviesEntity>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromResultsToJson(list: List<TopRatedMoviesEntity>?): String {
        return Gson().toJson(list)
    }

}
