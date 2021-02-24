package com.kodingan.endemic.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kodingan.endemic.core.domain.usecase.SpeciesUseCase

class HomeViewModel(speciesUseCase: SpeciesUseCase) : ViewModel() {
    val species = speciesUseCase.getAllSpecies().asLiveData()
}

