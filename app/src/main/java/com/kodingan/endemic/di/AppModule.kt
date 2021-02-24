package com.kodingan.endemic.di

import com.kodingan.endemic.core.domain.usecase.SpeciesInteractor
import com.kodingan.endemic.core.domain.usecase.SpeciesUseCase
import com.kodingan.endemic.detail.DetailSpeciesViewModel

import com.kodingan.endemic.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeViewModel(get()) }

    viewModel { DetailSpeciesViewModel(get()) }
}

val useCaseModule = module {
    factory<SpeciesUseCase> { SpeciesInteractor(get()) }
}

