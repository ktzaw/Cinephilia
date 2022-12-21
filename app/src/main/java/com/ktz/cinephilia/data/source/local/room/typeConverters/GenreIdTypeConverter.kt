package com.ktz.cinephilia.data.source.local.room.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdTypeConverter {

    @TypeConverter
    fun fromJsonToGenreIds(json: String): List<Int>? {
        val typeToken = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromGenreIdsToJson(list: List<Int>?): String {
        return Gson().toJson(list)
    }
}
