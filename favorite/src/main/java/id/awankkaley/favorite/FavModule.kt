package id.awankkaley.favorite

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FavViewModel(get()) }
}