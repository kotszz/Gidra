package dev.kamenivska.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.kamenivska.myapplication.data.room.dao.TrainingsDao
import dev.kamenivska.myapplication.data.room.model.Training

@Database(entities = [Training::class], version = 1)
@TypeConverters(Converters::class)
abstract class GidraDatabase: RoomDatabase() {
    abstract fun trainingsDao(): TrainingsDao
}