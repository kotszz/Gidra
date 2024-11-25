package dev.kamenivska.myapplication.domain.trainings.impl

import dev.kamenivska.myapplication.data.repository.trainings.TrainingsRepository
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.domain.trainings.DeleteTrainingUseCase
import dev.kamenivska.myapplication.domain.trainings.InsertTrainingUseCase

class DeleteTrainingUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): DeleteTrainingUseCase {
    override fun invoke(training: Training): Int = trainingsRepository.deleteTraining(training)
}