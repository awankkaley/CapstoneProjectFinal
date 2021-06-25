package id.awankkaley.capstoneproject.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.awankkaley.core.data.Resource
import id.awankkaley.core.domain.model.Popular
import id.awankkaley.core.domain.usecase.PopularUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class HomeViewModel(private val popularUseCase: PopularUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow<Resource<List<Popular>>>(Resource.Loading())
    val uiState: StateFlow<Resource<List<Popular>>> = _uiState

    init {
        viewModelScope.launch {
            popularUseCase.getAllPopular().collect {
                _uiState.value = it
            }
        }
    }


}

