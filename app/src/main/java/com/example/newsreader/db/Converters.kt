package com.example.newsreader.db

import androidx.room.TypeConverter
import com.example.newsreader.models.Source

/**
 * Class to convert type Source to type String
 * @author 956013
 */

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}