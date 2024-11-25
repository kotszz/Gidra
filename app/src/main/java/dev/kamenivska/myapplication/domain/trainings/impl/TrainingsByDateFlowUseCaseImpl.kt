package dev.kamenivska.myapplication.domain.trainings.impl

import dev.kamenivska.myapplication.data.repository.trainings.TrainingsRepository
import dev.kamenivska.myapplication.data.room.model.Training
import dev.kamenivska.myapplication.domain.trainings.TrainingsByDateFlowUseCase
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrainingsByDateFlowUseCaseImpl(
    private val trainingsRepository: TrainingsRepository
): TrainingsByDateFlowUseCase {
    override fun invoke(startDate: LocalDate, endDate: LocalDate): Flow<List<Training>> =
        trainingsRepository.getTrainingsByDate(startDate, endDate)
}