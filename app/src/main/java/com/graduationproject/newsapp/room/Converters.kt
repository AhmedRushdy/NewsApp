package com.graduationproject.newsapp.room

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromSource(source: com.graduationproject.newsapp.models.Source):String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String): com.graduationproject.newsapp.models.Source {
        return com.graduationproject.newsapp.models.Source(name, name)
    }
}