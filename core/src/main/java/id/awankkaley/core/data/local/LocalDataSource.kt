package id.awankkaley.core.data.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val popularDao: PopularDao) {

    fun getAllPopular(): Flow<List<PopularEntity>> = popularDao.getAllPopular()

    //    fun searchMoviews(query: String): Flow<List<PopularEntity>> = popularDao.searchMovies(query)
    fun getFavoritePopular(): Flow<List<PopularEntity>> = popularDao.getFavoritePopular()

    fun isFavorite(id: String): Flow<PopularEntity?> = popularDao.isFavorite(id)


    suspend fun insertPopular(tourismList: List<PopularEntity>) =
        popularDao.insertPopular(tourismList)

    fun setFavoritePopular(favorite: FavoriteEntity, newState: Boolean) {
        if (newState) {
            popularDao.insertFavorite(favorite)
        } else {
            popularDao.deleteFavorite(favorite)
        }
    }
}