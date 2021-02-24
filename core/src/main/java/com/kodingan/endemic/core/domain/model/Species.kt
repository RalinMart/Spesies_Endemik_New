package com.kodingan.endemic.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Species(
        val isFavorite: Boolean,
        val speciesId: String,
        val image: String,
        val name: String,
        val description: String,
        val address: String,
        val latitude: Double,
        val longitude: Double,
        val like: Int,


) : Parcelable