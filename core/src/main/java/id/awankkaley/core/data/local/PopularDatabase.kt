package id.awankkaley.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.awankkaley.capstoneproject.util.Converter

@Database(
    entities = [PopularEntity::class, FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class PopularDatabase : RoomDatabase() {

    abstract fun popularDao(): PopularDao
}