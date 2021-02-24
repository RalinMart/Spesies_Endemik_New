package com.kodingan.endemic.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "species")
data class SpeciesEntity(
        @PrimaryKey
    @NonNull
    @ColumnInfo(name = "speciesId")
    var speciesId: String,

        @ColumnInfo(name = "name")
    var name: String,

        @ColumnInfo(name = "description")
    var description: String,

        @ColumnInfo(name = "address")
    var address: String,

        @ColumnInfo(name = "latitude")
    var latitude: Double,

        @ColumnInfo(name = "longitude")
    var longitude: Double,

        @ColumnInfo(name = "like")
    var like: Int,

        @ColumnInfo(name = "image")
    var image: String,

        @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)
