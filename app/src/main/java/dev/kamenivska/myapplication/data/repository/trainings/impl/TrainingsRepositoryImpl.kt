package dev.kamenivska.myapplication.data.repository.trainings.impl

import dev.kamenivska.myapplication.data.repository.trainings.TrainingsRepository
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.service.TrainingsService
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrainingsRepositoryImpl(
    private val service: TrainingsService
) : TrainingsRepository {
    override fun getTrainingsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<Training>> = service.getTrainingsByDate(startDate, endDate)

    override fun insertTraining(training: Training) = service.insertTraining(training)

    override fun getClosestTraining(now: LocalDate): Training = service.getClosestTraining(now)

    override fun getTrainings(): Flow<List<Training>> = service.getTrainings()

    override fun deleteTraining(training: Training): Int = service.deleteTraining(training)
}