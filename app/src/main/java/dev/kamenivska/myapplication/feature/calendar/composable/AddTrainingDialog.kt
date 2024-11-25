package dev.kamenivska.myapplication.feature.calendar.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.feature.calendar.timeslots
import dev.kamenivska.myapplication.main.utils.model.CalendarDay
import dev.kamenivska.myapplication.main.utils.pxToDp
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.elements.GidraSwitch
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import kotlinx.coroutines.launch

@Composable
fun AddTrainingDialog(
    openDialogState: MutableState<Boolean>,
    onAddClick: () -> Unit,
    calendarDays: List<CalendarDay>,
    selectedTrainingType: TrainingType?,
    onDateClick: (Int) -> Unit,
    onTrainingClick: (TrainingType) -> Unit,
    onRepeatEveryWeekClick: (Boolean) -> Unit,
) {

    val timeslotsState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val itemSize = remember {
        mutableStateOf(0.dp)
    }

    if (openDialogState.value) {
        Dialog(
            onDismissRequest = {
                openDialogState.value = false
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                modifier = Modifier
                    .background(DialogBackground, RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .padding(32.dp),
            ) {
                Row {
                    Text(
                        text = "Add training",
                        fontFamily = Outfit,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        lineHeight = 26.sp,
                        color = TextGrey,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .background(
                                color = PrimaryColor,
                                shape = CircleShape
                            )
                            .clickable { openDialogState.value = false },
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_cross),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = DefaultBackground)
                        )
                    }
                }

                Text(
                    modifier = Modifier.padding(top = 36.dp),
                    text = "Choose date:",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = TextGrey,
                )

                SmallCalendarView(
                    modifier = Modifier.padding(top = 12.dp),
                    calendarDays = calendarDays,
                    onClick = onDateClick
                )

                Text(
                    modifier = Modifier.padding(top = 28.dp),
                    text = "Choose date:",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = TextGrey,
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.clickable {
                            timeslotsState.apply {
                                coroutineScope.launch {
                                    if (firstVisibleItemIndex != 0) {
                                        animateScrollToItem(firstVisibleItemIndex - 1, 0)
                                    }
                                }
                            }
                        },
                        painter = painterResource(R.drawable.ic_chevron_left),
                        contentDescription = null,
                    )

                    LazyRow(
                        state = timeslotsState,
                        userScrollEnabled = false,
                        modifier = Modifier.width(itemSize.value + 24.dp)
                    ) {
                        items(timeslots.count()) { index ->
                            Text(
                                modifier = Modifier.padding(12.dp).onSizeChanged {
                                    itemSize.value = it.width.toFloat().pxToDp(context)
                                },
                                text = timeslots[index],
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Normal,
                                fontSize = 15.sp,
                                color = TextGrey,
                            )
                        }
                    }

                    Image(
                        modifier = Modifier.clickable {
                            timeslotsState.apply {
                                coroutineScope.launch {
                                    if (firstVisibleItemIndex != calendarDays.size - 1 ) {
                                        animateScrollToItem(firstVisibleItemIndex + 1, 0)
                                    }
                                }
                            }
                        },
                        painter = painterResource(R.drawable.ic_chevron_right),
                        contentDescription = null,
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 28.dp),
                    text = "Choose type:",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = TextGrey,
                )

                Row(
                    modifier = Modifier.padding(top = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.clickable {
                            onTrainingClick(TrainingType.DYN)
                        },
                        painter = painterResource(R.drawable.ic_crab),
                        contentDescription = null,
                        colorFilter = if (selectedTrainingType == TrainingType.DYN)
                            ColorFilter.tint(PrimaryColor)
                        else
                            ColorFilter.tint(TextGrey)
                    )

                    Spacer(modifier = Modifier.size(32.dp))

                    Image(
                        modifier = Modifier.clickable {
                            onTrainingClick(TrainingType.DYNB)
                        },
                        painter = painterResource(R.drawable.ic_flippers),
                        contentDescription = null,
                        colorFilter = if (selectedTrainingType == TrainingType.DYNB)
                            ColorFilter.tint(PrimaryColor)
                        else
                            ColorFilter.tint(TextGrey)
                    )

                    Spacer(modifier = Modifier.size(32.dp))

                    Image(
                        modifier = Modifier.clickable {
                            onTrainingClick(TrainingType.COACHING)
                        },
                        painter = painterResource(R.drawable.ic_coaching),
                        contentDescription = null,
                        colorFilter = if (selectedTrainingType == TrainingType.COACHING)
                            ColorFilter.tint(PrimaryColor)
                        else
                            ColorFilter.tint(TextGrey)
                    )

                    Spacer(modifier = Modifier.size(32.dp))

                    Image(
                        modifier = Modifier.clickable {
                            onTrainingClick(TrainingType.STA)
                        },
                        painter = painterResource(R.drawable.ic_lungs),
                        contentDescription = null,
                        colorFilter = if (selectedTrainingType == TrainingType.STA)
                            ColorFilter.tint(PrimaryColor)
                        else
                            ColorFilter.tint(TextGrey)
                    )

                    Spacer(modifier = Modifier.size(32.dp))

                    Image(
                        modifier = Modifier.clickable {
                            onTrainingClick(TrainingType.DNF)
                        },
                        painter = painterResource(R.drawable.ic_mask),
                        contentDescription = null,
                        colorFilter = if (selectedTrainingType == TrainingType.DNF)
                            ColorFilter.tint(PrimaryColor)
                        else
                            ColorFilter.tint(TextGrey)
                    )

                }

                Row(
                    modifier = Modifier.padding(top = 28.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Repeat every week:",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = TextGrey,
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    GidraSwitch (
                        height = 24.dp,
                        width = 40.dp,
                        circleButtonPadding = 4.dp,
                        initialValue = 0,
                        onCheckedChanged = onRepeatEveryWeekClick
                    )
                }

                GidraButton(
                    modifier = Modifier.padding(top = 32.dp),
                    text = "Add training",
                    onClick = {
                        onAddClick()
                    }
                )
            }
        }
    }
}