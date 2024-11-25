package dev.kamenivska.myapplication.domain.trainings

import dev.kamenivska.myapplication.data.room.model.Training

interface InsertTrainingUseCase {
    operator fun invoke(training: Training): Long
}