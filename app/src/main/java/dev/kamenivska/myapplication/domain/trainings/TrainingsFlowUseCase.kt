package dev.kamenivska.myapplication.domain.trainings

import dev.kamenivska.myapplication.data.room.model.Training
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrainingsFlowUseCase {
    operator fun invoke(): Flow<List<Training>>
}