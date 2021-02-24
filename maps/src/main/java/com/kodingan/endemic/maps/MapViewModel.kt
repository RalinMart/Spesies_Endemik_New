package com.kodingan.endemic.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kodingan.endemic.core.domain.usecase.SpeciesUseCase

class MapViewModel(speciesUseCase: SpeciesUseCase) : ViewModel() {
    val species = speciesUseCase.getAllSpecies().asLiveData()
}

