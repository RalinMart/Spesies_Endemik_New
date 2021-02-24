package com.kodingan.endemic.core.data.source.local.room

import androidx.room.*
import com.kodingan.endemic.core.data.source.local.entity.SpeciesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SpeciesDao {

    @Query("SELECT * FROM species")
    fun getAllSpecies(): Flow<List<SpeciesEntity>>

    @Query("SELECT * FROM species where isFavorite = 1")
    fun getFavoriteSpecies(): Flow<List<SpeciesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(species: List<SpeciesEntity>)

    @Update
    fun updateFavoriteSpecies(species: SpeciesEntity)
}
