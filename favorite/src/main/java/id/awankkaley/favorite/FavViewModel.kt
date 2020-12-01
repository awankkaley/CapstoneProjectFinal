package id.awankkaley.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.awankkaley.core.domain.usecase.PopularUseCase

class FavViewModel(popularUseCase: PopularUseCase) : ViewModel() {
    val favorite = popularUseCase.getFavoritePopular().asLiveData()
}