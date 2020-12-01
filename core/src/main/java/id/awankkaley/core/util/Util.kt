package id.awankkaley.core.util

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object{
        const val base_url = "https://api.themoviedb.org/3/"
        const val popular = "movie/popular"
        const val search = "search/movie"
        const val image_base_url = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "e9cc45e8acd602e06c0139685d7bc311"

        @SuppressLint("SimpleDateFormat")
        fun convertOnlyYear(date: String?): String {
            return try {
                val oldFormat = SimpleDateFormat("yyyy-MM-dd")
                val newFormat = SimpleDateFormat("yyyy", Locale("ID"))
                val obj = oldFormat.parse(date.toString())
                (newFormat.format(obj!!))
            }catch (e : Exception){
                "No Data"
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun convertDate(date: String?): String {
            return try {
                val oldFormat = SimpleDateFormat("yyyy-MM-dd")
                val newFormat = SimpleDateFormat("dd.MM.yyyy", Locale("ID"))
                val obj = oldFormat.parse(date.toString())
                (newFormat.format(obj!!))
            }catch (e : Exception){
                "No Data"
            }
        }
    }
}