package id.awankkaley.capstoneproject.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.awankkaley.core.domain.usecase.PopularUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class SearchViewModel(popularUseCase: PopularUseCase) : ViewModel() {
    @FlowPreview
    @ExperimentalCoroutinesApi
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @ExperimentalCoroutinesApi
    @FlowPreview
    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            popularUseCase.searchMovies(it)
        }
        .asLiveData()
}