package id.awankkaley.core.domain.usecase

import id.awankkaley.core.data.Resource
import id.awankkaley.core.data.remote.response.PopularItem
import id.awankkaley.core.domain.model.Popular
import kotlinx.coroutines.flow.Flow

interface PopularUseCase {
    fun getAllPopular(): Flow<Resource<List<Popular>>>
    fun searchMovies(query: String): Flow<Resource<List<Popular>>>
    fun getFavoritePopular(): Flow<List<Popular>>
    fun setFavoritePopular(popular: Popular, state: Boolean)
    fun isFavorite(id: String): Flow<Boolean>
}