package dev.kamenivska.myapplication.main.navigation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.main.navigation.main.bottomnav.NavigationItem
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import org.koin.compose.getKoin

@Preview(showSystemUi = true)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToAuth: () -> Unit = {},
) {

    Scaffold(modifier = modifier) { innerPadding ->

        val mainNavController = rememberNavController()
        getKoin().setProperty("mainNavController", mainNavController)

        val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route.orEmpty()

        val navItems = listOf(
            NavigationItem.Home(),
            NavigationItem.Train(),
            NavigationItem.Logbook(),
            NavigationItem.Coaching(),
            NavigationItem.Profile()
        )

        Box {
            MainNavGraph(
                modifier = Modifier
                    .padding(innerPadding),
                navController = mainNavController,
                navigateToAuth = navigateToAuth,
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                Spacer(modifier = Modifier.weight(1f))

                if (
                    navItems.any { it.route == currentRoute }
                ) {
                    Box(
                        modifier = Modifier
                            .height(IntrinsicSize.Max),
                    ) {
                        Image(
                            modifier = Modifier
                                .fillMaxWidth()
                                .absoluteOffset(0.dp, 1.dp),
                            painter = painterResource(R.drawable.bg_bottom_navigation),
                            contentScale = ContentScale.FillWidth,
                            contentDescription = null,
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(horizontal = 24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            navItems.forEach { item ->
                                NavigationItem(
                                    navigationItem = item,
                                    isSelected = item.route == currentRoute
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(innerPadding.calculateBottomPadding())
                        .background(PrimaryColor)
                    )
                }
            }
        }
    }
}