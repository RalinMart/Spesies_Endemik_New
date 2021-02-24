package com.kodingan.endemic.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kodingan.endemic.core.data.source.local.entity.SpeciesEntity

@Database(entities = [SpeciesEntity::class], version = 1, exportSchema = false)
abstract class SpeciesDatabase : RoomDatabase() {

    abstract fun speciesDao(): SpeciesDao

}