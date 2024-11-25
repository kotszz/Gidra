package dev.kamenivska.myapplication.feature.premium

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.feature.train.composable.Header
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey

@Composable
@Preview
fun GetProScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(32.dp),
        ) {
            Header(
                text = "get pro",
                onNavigateBack = navigateBack
            )

            Row(
                modifier = Modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth()
                    .background(DialogBackground, RoundedCornerShape(16.dp)),
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
                        color = PrimaryColor,
                    )
                    Text(
                        text = "20$/month",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = PrimaryColor,
                    )

                    Text(
                        text = "Perfect for beginners",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        color = Color.White,
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = DefaultBackground, CircleShape)
                )

                Spacer(modifier = Modifier.size(28.dp))
            }

            Column(
                modifier = Modifier.widthIn(max = 240.dp).padding(top = 32.dp),
            ) {
                Text(
                    text = "Unlock All Features Of The Application With PRO Subscription:",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    color = TextGrey,
                )

                Row {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(4.dp)
                            .background(
                                color = TextGrey,
                                shape = CircleShape,
                            )
                    )

                    Text(
                        text = "Practice All Tables Without Any Limitations",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = TextGrey,
                    )
                }

                Row {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(4.dp)
                            .background(
                                color = TextGrey,
                                shape = CircleShape,
                            )
                    )

                    Text(
                        text = "Training Sessions For Dynamics, Depth And Equalization",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = TextGrey,
                    )
                }

                Row {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(4.dp)
                            .background(
                                color = TextGrey,
                                shape = CircleShape,
                            )
                    )

                    Text(
                        text = "Online Events",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = TextGrey,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .padding(top = 48.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = PrimaryColor,
                            shape = RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp,)
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(vertical = 10.dp, horizontal = 4.dp),
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                        )
                        Text(
                            text = "Recommended",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            lineHeight = 21.sp,
                            color = Color.White,
                        )
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DialogBackground, RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.size(28.dp))

                    Column(
                        modifier = Modifier.padding(vertical = 16.dp),
                    ) {
                        Text(
                            text = "Get PRO+",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            color = PrimaryColor,
                        )
                        Text(
                            text = "50$/month",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            color = PrimaryColor,
                        )

                        Text(
                            text = "Perfect for PRO",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Normal,
                            fontSize = 10.sp,
                            lineHeight = 15.sp,
                            color = Color.White,
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(color = DefaultBackground, CircleShape)
                    )

                    Spacer(modifier = Modifier.size(28.dp))
                }
            }

            Column(
                modifier = Modifier.widthIn(max = 240.dp).padding(top = 32.dp),
            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(4.dp)
                            .background(
                                color = TextGrey,
                                shape = CircleShape,
                            )
                    )

                    Text(
                        text = "Get Free Coaching Sessions",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = TextGrey,
                    )
                }

                Row {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(4.dp)
                            .background(
                                color = TextGrey,
                                shape = CircleShape,
                            )
                    )

                    Text(
                        text = "Get Access To Community",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = TextGrey,
                    )
                }

                Row {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(4.dp)
                            .background(
                                color = TextGrey,
                                shape = CircleShape,
                            )
                    )

                    Text(
                        text = "Exclusive Events",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        color = TextGrey,
                    )
                }
            }
        }

        Image(
            modifier = Modifier
                .padding(top = 12.dp),
            painter = painterResource(R.drawable.img_transparent_waves),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
    }
}