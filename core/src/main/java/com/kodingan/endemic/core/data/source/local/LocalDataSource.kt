package com.kodingan.endemic.core.data.source.local

import com.kodingan.endemic.core.data.source.local.entity.SpeciesEntity
import com.kodingan.endemic.core.data.source.local.room.SpeciesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val speciesDao: SpeciesDao) {

    fun getAllSpecies(): Flow<List<SpeciesEntity>> = speciesDao.getAllSpecies()

    fun getFavoriteSpecies(): Flow<List<SpeciesEntity>> = speciesDao.getFavoriteSpecies()

    suspend fun insertSpecies(speciesList: List<SpeciesEntity>) = speciesDao.insertSpecies(speciesList)

    fun setFavoriteSpecies(species: SpeciesEntity, newState: Boolean) {
        species.isFavorite = newState
        speciesDao.updateFavoriteSpecies(species)
    }
}