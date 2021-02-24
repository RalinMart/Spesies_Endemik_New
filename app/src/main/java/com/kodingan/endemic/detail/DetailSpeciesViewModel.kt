package com.kodingan.endemic.detail

import androidx.lifecycle.ViewModel
import com.kodingan.endemic.core.domain.model.Species
import com.kodingan.endemic.core.domain.usecase.SpeciesUseCase

class DetailSpeciesViewModel(private val speciesUseCase: SpeciesUseCase) : ViewModel() {
    fun setFavoriteSpecies(species: Species, newStatus:Boolean) =
        speciesUseCase.setFavoriteSpecies(species, newStatus)
}


