package id.awankkaley.capstoneproject.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.awankkaley.core.data.Resource
import id.awankkaley.core.domain.model.Popular
import id.awankkaley.core.domain.usecase.PopularUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class DetailViewModel(private val popularUseCase: PopularUseCase) : ViewModel() {
    val popularData = MutableLiveData<Popular>()

    fun sendPopularForDetail(data: Popular) {
        popularData.value = data
    }

    private val _uiState = MutableStateFlow<Resource<List<Popular>>>(Resource.Loading())
    val uiState: StateFlow<Resource<List<Popular>>> = _uiState

    init {
        viewModelScope.launch {
            popularUseCase.getFavoritePopular().collect {
                if (it.isNotEmpty())
                    _uiState.value = Resource.Success(it)
                else
                    _uiState.value = Resource.Error("EMPTY")
            }
        }
    }

    fun setFavorite(popular: Popular, newStatus: Boolean) =
        popularUseCase.setFavoritePopular(popular, newStatus)

    private val _uiState2 = MutableStateFlow(false)
    val uiState2: StateFlow<Boolean> = _uiState2
    fun isFavorite(id: String) {
        viewModelScope.launch {
            popularUseCase.isFavorite(id).collect {
                _uiState2.value = it
            }
        }
    }
}