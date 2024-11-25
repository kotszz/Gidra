package dev.kamenivska.myapplication.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "trainings")
data class Training(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: LocalDate,
    val dayOfWeek: Int? = null,
    val type: TrainingType,
    val repeatEveryWeek: Boolean,
)
