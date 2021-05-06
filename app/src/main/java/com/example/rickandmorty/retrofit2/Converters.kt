package com.example.rickandmorty.retrofit2

import androidx.room.TypeConverter
import com.example.rickandmorty.pojo.Heroes
import com.example.rickandmorty.pojo.Info
import com.example.rickandmorty.pojo.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.emptyFlow
import java.util.*

class Converters {
    companion object {
        val gson = Gson()

        @TypeConverter
        @JvmStatic
        fun stringToHeroesObjectList(data: String?): List<Heroes?>? {
            if (data == null) {
                return emptyList<Heroes>()
            }
            val listType = object : TypeToken<List<Heroes?>?>() {}.type
            return Converters.gson.fromJson<List<Heroes>>(data, listType)
        }

        @TypeConverter
        @JvmStatic
        fun weatherObjectListToString(someObjects: List<Heroes?>?): String? {
            return gson.toJson(someObjects)
        }

        @TypeConverter
        @JvmStatic
        fun stringToInfoObject(dataInfo: String?): Info? {
            if (dataInfo == null) {
                val info: Info? = null
                return info
            }
            val listType = object : TypeToken<Info?>() {}.type
            return Converters.gson.fromJson<Info>(dataInfo, listType)
        }

        @TypeConverter
        @JvmStatic
        fun infoObjectToString(infoObjects: Info?): String? {
            return gson.toJson(infoObjects)
        }


    }
}
