package com.kodingan.endemic.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kodingan.endemic.core.domain.usecase.SpeciesUseCase

class FavoriteViewModel(speciesUseCase: SpeciesUseCase) : ViewModel() {
    val favoriteSpecies = speciesUseCase.getFavoriteSpecies().asLiveData()
}