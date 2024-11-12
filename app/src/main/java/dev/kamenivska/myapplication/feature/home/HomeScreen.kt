package dev.kamenivska.myapplication.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.data.room.model.TrainingType
import dev.kamenivska.myapplication.main.utils.getDayMonthYear
import dev.kamenivska.myapplication.ui.elements.CalendarView
import dev.kamenivska.myapplication.ui.elements.GidraResizableButton
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import org.koin.androidx.compose.koinViewModel
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToCalendar: () -> Unit = {},
) {

    val viewModel: HomeViewModel = koinViewModel()
    val calendarDays by viewModel.calendarDaysList.collectAsStateWithLifecycle()
    val monthAndYear by viewModel.currentMonthAndYear.collectAsStateWithLifecycle()
    val closestTraining by viewModel.closestTraining.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {

        closestTraining?.let {
            Row(
                modifier = Modifier
                    .padding(top = 32.dp, start = 32.dp, end = 32.dp)
                    .fillMaxWidth()
                    .background(PrimaryColor, RoundedCornerShape(16.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(28.dp))
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = "Up Next",
                    fontFamily = Outfit,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    color = DefaultBackground,
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(20.dp),
                    painter = when(it.type) {
                        TrainingType.DYN -> painterResource(R.drawable.ic_crab)
                        TrainingType.DYNB -> painterResource(R.drawable.ic_flippers)
                        TrainingType.COACHING -> painterResource(R.drawable.ic_coaching)
                        TrainingType.STA -> painterResource(R.drawable.ic_lungs)
                        TrainingType.DNF -> painterResource(R.drawable.ic_mask)
                    },
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = DefaultBackground),
                )
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = it.date.run {
                        dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH).uppercase() +
                                " " + getDayMonthYear()
                    },
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    lineHeight = 15.sp,
                    color = DefaultBackground,
                )
                Image(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(DefaultBackground)
                )
                Spacer(modifier = Modifier.size(28.dp))
            }

        }

        Column(
            modifier = Modifier
                .padding(top = 8.dp, start = 32.dp, end = 32.dp)
                .fillMaxWidth()
                .background(
                    DialogBackground,
                    RoundedCornerShape(16.dp)
                )
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(12.dp)
                        .clickable {
                            viewModel.onPreviousClick()
                        },
                    painter = painterResource(R.drawable.ic_chevron_left),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = monthAndYear,
                    fontFamily = Outfit,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    lineHeight = 20.sp,
                    color = PrimaryColor,
                )

                Image(
                    modifier = Modifier
                        .size(12.dp)
                        .clickable {
                            viewModel.onNextClick()
                        },
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.weight(1F))

                Box(
                    modifier = Modifier
                        .background(PrimaryColor, CircleShape)
                        .clickable(onClick = navigateToCalendar)

                ) {
                    Image(
                        modifier = Modifier.padding(4.dp),
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = null,
                    )
                }
            }

            CalendarView(
                modifier = Modifier.padding(bottom = 24.dp, start = 16.dp, end = 16.dp),
                calendarDays = calendarDays
            )
        }

        Row(
            modifier = Modifier
                .padding(vertical = 28.dp, horizontal = 32.dp)
                .fillMaxWidth()
                .background(PrimaryColor, RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(28.dp))

            Column(
                modifier = Modifier.padding(vertical = 16.dp),
            ) {
                Text(
                    text = "Get PRO",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = DefaultBackground,
                )
                Text(
                    text = "20$/month",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = DefaultBackground,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Box(modifier = Modifier
                .size(56.dp)
                .background(color = DefaultBackground, CircleShape)
            )

            Spacer(modifier = Modifier.size(28.dp))
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "My daily tips",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = TextGrey,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = "View all",
                fontFamily = Poppins,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = PrimaryColor,
            )
        }

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(top = 16.dp, start = 32.dp, end = 32.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box {
                Image(
                    modifier = Modifier.height(115.dp).width(200.dp).clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.img_stay_calm),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 32.dp, start = 16.dp),
                    text = "How to stay calm",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    color = TextGrey,
                )
            }

            Box {
                Image(
                    modifier = Modifier.height(115.dp).width(200.dp).clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(R.drawable.img_relax),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(bottom = 32.dp, start = 16.dp),
                    text = "Relax with me",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    color = TextGrey,
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 28.dp, start = 32.dp, end = 32.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Up next online",
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = TextGrey,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier.padding(end = 16.dp),
                text = "View all",
                fontFamily = Poppins,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                color = PrimaryColor,
            )
        }

        Box(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 32.dp,
                    end = 32.dp,
                    bottom = 112.dp
                )
                .fillMaxWidth(),
        ) {
            Image(
                modifier = Modifier.height(168.dp).fillMaxWidth().clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.img_event),
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 16.dp, start = 16.dp),
            ) {
                Text(
                    text = "EQ Workshop",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = TextGrey,
                )
                Text(
                    text = "Loic Vuillemin",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    color = TextGrey,
                )
                Text(
                    text = "12/07/25",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = TextGrey,
                )
            }

            GidraResizableButton(
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
                textPadding = PaddingValues(vertical = 8.dp, horizontal = 32.dp),
                text = "Join"
            ) { }
        }
    }
}

@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}