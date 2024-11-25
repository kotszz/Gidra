package dev.kamenivska.myapplication.domain.trainings.impl

import dev.kamenivska.myapplication.data.repository.trainings.TrainingsRepository
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.domain.trainings.TrainingsFlowUseCase
import kotlinx.coroutines.flow.Flow

class TrainingsFlowUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): TrainingsFlowUseCase {
    override fun invoke(): Flow<List<Training>> =
        trainingsRepository.getTrainings()
}