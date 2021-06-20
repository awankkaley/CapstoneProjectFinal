package id.awankkaley.capstoneproject.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.awankkaley.core.domain.model.Popular
import id.awankkaley.core.domain.usecase.PopularUseCase

class DetailViewModel(private val popularUseCase: PopularUseCase) : ViewModel() {
    fun setFavorite(popular: Popular, newStatus: Boolean) =
        popularUseCase.setFavoritePopular(popular, newStatus)

    fun isFavorite(id: String) = popularUseCase.isFavorite(id).asLiveData()
}
