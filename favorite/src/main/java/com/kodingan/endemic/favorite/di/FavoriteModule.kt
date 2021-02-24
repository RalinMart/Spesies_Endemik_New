package com.kodingan.endemic.favorite.di


import com.kodingan.endemic.favorite.FavoriteViewModel

import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}