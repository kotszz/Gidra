package dev.kamenivska.myapplication.domain.trainings.impl

import dev.kamenivska.myapplication.data.repository.trainings.TrainingsRepository
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.domain.trainings.GetClosestTrainingUseCase
import java.time.LocalDate

class GetClosestTrainingUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): GetClosestTrainingUseCase {
    override fun invoke(): Training = trainingsRepository.getClosestTraining(LocalDate.now())
}