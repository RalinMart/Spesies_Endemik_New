package com.kodingan.endemic.core.domain.usecase

import com.kodingan.endemic.core.data.Resource
import com.kodingan.endemic.core.domain.model.Species
import kotlinx.coroutines.flow.Flow

interface SpeciesUseCase {

    fun setFavoriteSpecies(species: Species, state: Boolean)
    fun getAllSpecies(): Flow<Resource<List<Species>>>
    fun getFavoriteSpecies(): Flow<List<Species>>

}