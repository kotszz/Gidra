package dev.kamenivska.myapplication.main.utils

fun Long.formatTime(): String {
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}