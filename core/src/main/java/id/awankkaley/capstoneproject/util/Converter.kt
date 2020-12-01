package id.awankkaley.capstoneproject.util

import androidx.room.TypeConverter


class Converter {
    @TypeConverter
    fun listtoString(value: List<Int>): String {
        var values = ""
        for (ids in value) values += "$ids,"
        return values
    }

    @TypeConverter
    fun stringToList(value: String): List<Int> {
        return value.map { it.toInt() }
    }
}