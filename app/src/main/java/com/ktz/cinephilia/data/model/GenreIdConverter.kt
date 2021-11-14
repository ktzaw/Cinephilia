package com.ktz.cinephilia.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class GenreIdConverter {

    private val gson = Gson()

    @TypeConverter
    fun stringToGenreList(data: String?): List<MovieDetail.Genre> {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun genreListToString(genreIds: List<MovieDetail.Genre>?): String {
        if (genreIds == null) {
            return gson.toJson(Collections.emptyList<Int>())
        }
        return gson.toJson(genreIds)
    }
}

