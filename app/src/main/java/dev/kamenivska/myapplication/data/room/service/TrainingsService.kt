package dev.kamenivska.myapplication.data.room.service

import dev.kamenivska.myapplication.data.room.model.Training
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrainingsService {
    fun getTrainingsByDate(
        startDate: LocalDate,
        endDate: LocalDate,
    ): Flow<List<Training>>

    fun insertTraining(training: Training): Long

    fun getClosestTraining(now: LocalDate): Training
}