package dev.kamenivska.myapplication.domain.trainings

import dev.kamenivska.myapplication.data.room.model.Training

interface GetClosestTrainingUseCase {
    operator fun invoke(): Training
}