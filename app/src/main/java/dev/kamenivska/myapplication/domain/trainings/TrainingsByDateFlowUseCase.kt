package dev.kamenivska.myapplication.domain.trainings

import dev.kamenivska.myapplication.data.room.model.Training
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrainingsByDateFlowUseCase {
    operator fun invoke(startDate: LocalDate, endDate: LocalDate): Flow<List<Training>>
}