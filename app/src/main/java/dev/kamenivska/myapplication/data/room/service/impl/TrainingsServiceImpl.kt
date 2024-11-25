package dev.kamenivska.myapplication.data.room.service.impl

import dev.kamenivska.myapplication.data.room.dao.TrainingsDao
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.data.room.service.TrainingsService
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrainingsServiceImpl(
    private val dao: TrainingsDao
) : TrainingsService {
    override fun getTrainingsByDate(
        startDate: LocalDate,
        endDate: LocalDate
    ): Flow<List<Training>> = dao.getTrainingsByDate(startDate, endDate)

    override fun insertTraining(training: Training) = dao.insertTraining(training)

    override fun getClosestTraining(now: LocalDate): Training = dao.getClosestTraining(now)

    override fun getTrainings() = dao.getTrainings()

    override fun deleteTraining(training: Training): Int = dao.deleteTraining(training)
}