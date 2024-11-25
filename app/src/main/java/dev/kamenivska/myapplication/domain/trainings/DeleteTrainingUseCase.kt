package dev.kamenivska.myapplication.domain.trainings

import dev.kamenivska.myapplication.data.room.model.Training

interface DeleteTrainingUseCase {
    operator fun invoke(training: Training): Int
}