package dev.kamenivska.myapplication.main.utils

import android.content.Context
import androidx.compose.ui.unit.dp

fun Long?.toStringOrNull(): String? = this?.run { toString() }
fun Float.pxToDp(context: Context) = (this / context.resources.displayMetrics.density).dp
fun Long.formatTime(): String {
    val minutes = (this % 3600) / 60
    val remainingSeconds = this % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}