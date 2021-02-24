package com.kodingan.endemic.maps.di

import com.kodingan.endemic.maps.MapViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapsModule = module {
    viewModel { MapViewModel(get()) }
}