package id.awankkaley.capstoneproject.di

import id.awankkaley.capstoneproject.detail.DetailViewModel
import id.awankkaley.capstoneproject.home.HomeViewModel
import id.awankkaley.core.domain.usecase.PopularInteractor
import id.awankkaley.core.domain.usecase.PopularUseCase
import id.awankkaley.capstoneproject.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PopularUseCase> { PopularInteractor(get()) }
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}