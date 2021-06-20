package id.awankkaley.core.domain.repository

import id.awankkaley.core.data.Resource
import id.awankkaley.core.data.remote.response.PopularItem
import id.awankkaley.core.domain.model.Popular
import kotlinx.coroutines.flow.Flow

interface IPopularRepository {

    fun getAllPopular(): Flow<Resource<List<Popular>>>

     fun searchMovies(query:String): Flow<Resource<List<Popular>>>

    fun getFavoritePopular(): Flow<List<Popular>>

    fun setFavoritePopular(popular: Popular, state: Boolean)

}