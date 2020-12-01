package id.awankkaley.capstoneproject.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.awankkaley.core.domain.usecase.PopularUseCase


class HomeViewModel(popularUseCase: PopularUseCase) : ViewModel() {
    val popular = popularUseCase.getAllPopular().asLiveData()
}

