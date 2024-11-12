package dev.kamenivska.myapplication.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.kamenivska.myapplication.data.room.model.Training
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TrainingsDao {
    @Query("SELECT * FROM trainings WHERE date BETWEEN :startDate AND :endDate OR repeatEveryWeek LIKE 1")
    fun getTrainingsByDate(
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<List<Training>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTraining(training: Training): Long

    @Query("SELECT * FROM trainings WHERE date >= :now ORDER BY date ASC LIMIT 1")
    fun getClosestTraining(
        now: LocalDate,
    ): Training
}