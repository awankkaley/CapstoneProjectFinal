package id.awankkaley.capstoneproject.search

import androidx.lifecycle.ViewModel
import id.awankkaley.core.domain.usecase.PopularUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@ObsoleteCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(popularUseCase: PopularUseCase) : ViewModel() {

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @FlowPreview
    val searchResult = queryChannel.asFlow()
        .debounce(500)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            popularUseCase.searchMovies(it)
        }
}