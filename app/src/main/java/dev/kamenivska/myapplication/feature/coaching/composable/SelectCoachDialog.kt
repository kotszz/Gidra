package dev.kamenivska.myapplication.feature.coaching.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.Dialog
@Composable
fun SelectCoachDialog(
    openDialogState: MutableState<Boolean>,
    bookClicked: () -> Unit = {},
) {
    if (openDialogState.value) {
        Dialog(
            onDismissRequest = {
                openDialogState.value = false
            },
//            properties = DialogProperties(
//                dismissOnBackPress = false,
//                dismissOnClickOutside = false
//            )
        ) {
            CoachListView(
                bookClicked = bookClicked
            )
        }
    }
}