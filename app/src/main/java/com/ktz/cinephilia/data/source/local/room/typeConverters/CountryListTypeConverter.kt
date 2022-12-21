package com.ktz.cinephilia.data.source.local.room.typeConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CountryListTypeConverter {

    @TypeConverter
    fun fromJsonToCountryList(json: String): List<String>? {
        val typeToken = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, typeToken)
    }

    @TypeConverter
    fun fromCountryListToJson(list: List<String>?): String {
        return Gson().toJson(list)
    }
}
