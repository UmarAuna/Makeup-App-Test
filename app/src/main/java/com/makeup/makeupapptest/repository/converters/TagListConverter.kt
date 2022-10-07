package com.makeup.makeupapptest.repository.converters

import androidx.room.TypeConverter

class TagListConverter {
    @TypeConverter
    fun fromTagList(list: List<String>?): String {
        return list?.joinToString(separator = ",") { it } ?: ""
    }

    @TypeConverter
    fun toTagList(string: String?): List<String> {
        return ArrayList(string?.split(",")?.map { it } ?: emptyList())
    }
}