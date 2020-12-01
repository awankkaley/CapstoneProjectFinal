package id.awankkaley.core.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularDao {
    @Query("SELECT * FROM popular")
    fun getAllPopular(): Flow<List<PopularEntity>>

    @Query("SELECT * FROM popular where isFavorite = 1")
    fun getFavoritePopular(): Flow<List<PopularEntity>>

    @Query("SELECT * FROM popular where title LIKE :search")
    fun searchMovies(search:String): Flow<List<PopularEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopular(popular: List<PopularEntity>)

    @Update
    fun updateFavoritePopular(popular: PopularEntity)
}