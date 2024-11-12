package dev.kamenivska.myapplication.domain.trainings.impl

import dev.kamenivska.myapplication.data.repository.trainings.TrainingsRepository
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase

class InsertTrainingUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): InsertTrainingUseCase {
    override fun invoke(training: Training): Long = trainingsRepository.insertTraining(training)
}