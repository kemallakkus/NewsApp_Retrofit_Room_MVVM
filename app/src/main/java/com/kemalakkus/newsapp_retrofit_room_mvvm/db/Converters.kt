package com.kemalakkus.newsapp_retrofit_room_mvvm.db

import androidx.room.TypeConverter
import com.kemalakkus.newsapp_retrofit_room_mvvm.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}