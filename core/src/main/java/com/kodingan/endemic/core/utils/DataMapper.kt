package com.kodingan.endemic.core.utils

import com.kodingan.endemic.core.data.source.local.entity.SpeciesEntity
import com.kodingan.endemic.core.data.source.remote.response.SpeciesResponse
import com.kodingan.endemic.core.domain.model.Species

object DataMapper {
    fun mapResponsesToEntities(input: List<SpeciesResponse>): List<SpeciesEntity> {
        val speciesList = ArrayList<SpeciesEntity>()
        input.map {
            val species = SpeciesEntity(
                    isFavorite = false,
                    image = it.image,
                    speciesId = it.id,
                    description = it.description,
                    name = it.name,
                    address = it.address,
                    latitude = it.latitude,
                    longitude = it.longitude,
                    like = it.like


            )
            speciesList.add(species)
        }
        return speciesList
    }

    fun mapEntitiesToDomain(input: List<SpeciesEntity>): List<Species> =
        input.map {
            Species(
                    isFavorite = it.isFavorite,
                    image = it.image,
                    speciesId = it.speciesId,
                    description = it.description,
                    name = it.name,
                    address = it.address,
                    latitude = it.latitude,
                    longitude = it.longitude,
                    like = it.like


            )
        }

    fun mapDomainToEntity(input: Species) = SpeciesEntity(
        speciesId = input.speciesId,
            isFavorite = input.isFavorite,
            image = input.image,
            description = input.description,
            name = input.name,
            address = input.address,
            latitude = input.latitude,
            longitude = input.longitude,
            like = input.like


    )
}