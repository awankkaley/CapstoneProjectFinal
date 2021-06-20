package id.awankkaley.core.domain.usecase


import id.awankkaley.core.domain.model.Popular
import id.awankkaley.core.domain.repository.IPopularRepository
import kotlinx.coroutines.flow.Flow


class PopularInteractor(private val iPopularRepository: IPopularRepository) : PopularUseCase {

    override fun getAllPopular() = iPopularRepository.getAllPopular()

    override fun searchMovies(query: String) = iPopularRepository.searchMovies(query)

    override fun getFavoritePopular() = iPopularRepository.getFavoritePopular()

    override fun setFavoritePopular(popular: Popular, state: Boolean) =
        iPopularRepository.setFavoritePopular(popular, state)

    override fun isFavorite(id: String): Flow<Boolean> = iPopularRepository.isFavorite(id)
}