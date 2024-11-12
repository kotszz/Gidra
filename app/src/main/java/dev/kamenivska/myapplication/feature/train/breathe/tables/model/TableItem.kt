package dev.kamenivska.myapplication.feature.train.breathe.tables.model

import androidx.compose.runtime.Immutable

@Immutable
data class TableItem(
    val id: Int,
    val status: String,
    val time: Int,
    val state: ItemState = ItemState.DEFAULT,
)

val tableItemList = listOf(
    TableItem(
        id = 1,
        status = "Breathe",
        time = 5,
    ),
    TableItem(
        id = 2,
        status = "Hold",
        time = 3,
    ),
    TableItem(
        id = 3,
        status = "Breathe",
        time = 5,
    ),
    TableItem(
        id = 4,
        status = "Hold",
        time = 3,
    ),
    TableItem(
        id = 5,
        status = "Breathe",
        time = 5,
    ),
    TableItem(
        id = 6,
        status = "Hold",
        time = 3,
    ),
    TableItem(
        id = 7,
        status = "Breathe",
        time = 5,
    ),
    TableItem(
        id = 8,
        status = "Hold",
        time = 3,
    ),
    TableItem(
        id = 9,
        status = "Breathe",
        time = 5,
    ),
    TableItem(
        id = 10,
        status = "Hold",
        time = 3,
    ),
    TableItem(
        id = 11,
        status = "Breathe",
        time = 5,
    ),
    TableItem(
        id = 12,
        status = "Hold",
        time = 3,
    ),
)