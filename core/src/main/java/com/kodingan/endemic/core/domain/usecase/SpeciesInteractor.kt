package com.kodingan.endemic.core.domain.usecase

import com.kodingan.endemic.core.domain.model.Species
import com.kodingan.endemic.core.domain.repository.SpeciesRepository

class SpeciesInteractor(private val speciesRepository: SpeciesRepository): SpeciesUseCase {

    override fun setFavoriteSpecies(species: Species, state: Boolean) = speciesRepository.setFavoriteSpecies(species, state)

    override fun getAllSpecies() = speciesRepository.getAllSpecies()

    override fun getFavoriteSpecies() = speciesRepository.getFavoriteSpecies()


}