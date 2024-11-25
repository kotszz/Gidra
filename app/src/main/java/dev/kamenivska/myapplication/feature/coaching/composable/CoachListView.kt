package dev.kamenivska.myapplication.feature.coaching.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor

@Composable
fun CoachListView(
    modifier: Modifier = Modifier,
    bookClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .padding(top = 28.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        CoachListItem(
            avatar = painterResource(R.drawable.coach_4),
            name = "John Doe",
            level = "Middle",
            bookClicked = bookClicked
        )
        Spacer(modifier = Modifier.size(24.dp))
        CoachListItem(
            avatar = painterResource(R.drawable.coach_2),
            name = "Loic Vuillemin",
            level = "Pro",
            bookClicked = bookClicked
        )
        Spacer(modifier = Modifier.size(24.dp))
        CoachListItem(
            avatar = painterResource(R.drawable.coach_1),
            name = "Amy Brown",
            level = "Middle",
            bookClicked = bookClicked
        )
        Spacer(modifier = Modifier.size(24.dp))
        CoachListItem(
            avatar = painterResource(R.drawable.coach_3),
            name = "John Doe",
            level = "Middle",
            bookClicked = bookClicked
        )

        Spacer(modifier = Modifier.size(112.dp))

    }
}

@Composable
private fun CoachListItem(
    avatar: Painter,
    name: String,
    level: String,
    bookClicked: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = DialogBackground,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(top = 20.dp, start = 28.dp, end = 28.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                painter = avatar,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            Column(modifier = Modifier.padding(start = 16.dp)) {
                Text(
                    text = name,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    color = Color.White,
                )
                Text(
                    text = level,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    color = PrimaryColor,
                )
            }
        }

        GidraButton(
            modifier = Modifier
                .padding(bottom = 20.dp, top = 16.dp, start = 28.dp, end = 28.dp),
            text = "Book Now",
            onClick = bookClicked
        )

    }
}