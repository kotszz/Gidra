package dev.kamenivska.myapplication.data.repository.trainings

import dev.kamenivska.myapplication.data.room.model.Training
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrainingsRepository {
    fun getTrainingsByDate(
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<List<Training>>

    fun insertTraining(training: Training): Long

    fun getClosestTraining(now: LocalDate): Training
}