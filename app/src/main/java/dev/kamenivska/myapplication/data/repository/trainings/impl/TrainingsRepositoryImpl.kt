package dev.kamenivska.myapplication.data.repository.trainings.impl

import dev.kamenivska.myapplication.data.repository.trainings.TrainingsRepository
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.service.TrainingsService
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrainingsRepositoryImpl(
    val service: TrainingsService
) : TrainingsRepository {
    override fun getTrainingsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<Training>> = service.getTrainingsByDate(startDate, endDate)

    override fun insertTraining(training: Training) = service.insertTraining(training)

    override fun getClosestTraining(now: LocalDate): Training = service.getClosestTraining(now)
}