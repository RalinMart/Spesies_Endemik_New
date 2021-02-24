package com.kodingan.endemic.core.data

import com.kodingan.endemic.core.data.source.local.LocalDataSource
import com.kodingan.endemic.core.data.source.remote.RemoteDataSource
import com.kodingan.endemic.core.data.source.remote.network.ApiResponse
import com.kodingan.endemic.core.data.source.remote.response.SpeciesResponse
import com.kodingan.endemic.core.domain.model.Species
import com.kodingan.endemic.core.domain.repository.SpeciesRepository
import com.kodingan.endemic.core.utils.AppExecutors
import com.kodingan.endemic.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpeciesRepository(
        private val localDataSource: LocalDataSource,
        private val remoteDataSource: RemoteDataSource,
        private val appExecutors: AppExecutors
) : SpeciesRepository {

    override fun getAllSpecies(): Flow<Resource<List<Species>>> =
        object : NetworkBoundResource<List<Species>, List<SpeciesResponse>>() {
            override fun loadFromDB(): Flow<List<Species>> {
                return localDataSource.getAllSpecies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Species>?): Boolean =
//                data == null || data.isEmpty()
                 true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<SpeciesResponse>>> =
                remoteDataSource.getAllSpecies()

            override suspend fun saveCallResult(data: List<SpeciesResponse>) {
                val speciesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertSpecies(speciesList)
            }
        }.asFlow()

    override fun setFavoriteSpecies(species: Species, state: Boolean) {
        val speciesEntity = DataMapper.mapDomainToEntity(species)
        appExecutors.diskIO().execute { localDataSource.setFavoriteSpecies(speciesEntity, state) }
    }

    override fun getFavoriteSpecies(): Flow<List<Species>> {
        return localDataSource.getFavoriteSpecies().map {
           DataMapper.mapEntitiesToDomain(it)
        }
    }


}

