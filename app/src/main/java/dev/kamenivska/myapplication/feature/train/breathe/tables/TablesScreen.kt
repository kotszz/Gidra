package dev.kamenivska.myapplication.feature.train.breathe.tables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.kamenivska.myapplication.R
import dev.kamenivska.myapplication.feature.testbreath.ResultsSavedDialog
import dev.kamenivska.myapplication.feature.train.TrainResultsSavedDialog
import dev.kamenivska.myapplication.feature.train.breathe.tables.model.ItemState
import dev.kamenivska.myapplication.feature.train.breathe.tables.model.TableItem
import dev.kamenivska.myapplication.feature.train.composable.Header
import dev.kamenivska.myapplication.main.utils.formatTime
import dev.kamenivska.myapplication.ui.elements.GidraButton
import dev.kamenivska.myapplication.ui.theme.DefaultBackground
import dev.kamenivska.myapplication.ui.theme.DialogBackground
import dev.kamenivska.myapplication.ui.theme.Outfit
import dev.kamenivska.myapplication.ui.theme.Poppins
import dev.kamenivska.myapplication.ui.theme.PrimaryColor
import dev.kamenivska.myapplication.ui.theme.TextGrey
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun TablesScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit = {},
    navigateToLogbook: () -> Unit,
) {

    val viewModel: TablesViewModel = koinViewModel()

    val isInProgress by viewModel.isInProgress.collectAsStateWithLifecycle()
    val currentList by viewModel.currentList.collectAsStateWithLifecycle()
    val timer by viewModel.timer.collectAsStateWithLifecycle()

    val resultsSavedDialogState = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.onFinish.collectLatest {
            resultsSavedDialogState.value = true
        }
    }

    val density = LocalDensity.current

    val buttonHeightInDp = remember {
        mutableStateOf(0.dp)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            modifier = Modifier.padding(horizontal = 32.dp),
            text = "co2 table",
            onNavigateBack = navigateBack
        )

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = painterResource(R.drawable.tables_timer_bg),
                contentDescription = null,
            )
            Text(
                text = timer.formatTime(),
                fontFamily = Outfit,
                fontWeight = FontWeight.Normal,
                fontSize = 60.sp,
                color = TextGrey,
            )
        }

        Box (
            modifier = Modifier.fillMaxSize().padding(32.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(
                modifier = Modifier.padding(bottom = buttonHeightInDp.value + 92.dp),
            ) {
                LazySteps(
                    tableItems = currentList,
                )

                Spacer(Modifier.size(24.dp))

                LazyTable(
                    tableItems = currentList,
                )
            }

            GidraButton(
                modifier = Modifier
                    .padding(bottom = 64.dp)
                    .onSizeChanged {
                        buttonHeightInDp.value = density.run {
                            it.height.toDp()
                        }
                    },
                text = if(!isInProgress) "Start" else "Stop"
            ) {
                viewModel.onStartClick()
            }
        }
    }

    TrainResultsSavedDialog(
        openDialogState = resultsSavedDialogState,
        title = "TABLE"
    ) {
        navigateToLogbook()
    }

}

@Composable
fun LazySteps(
    modifier: Modifier = Modifier,
    tableItems: List<TableItem>
) {

    val pagerState = rememberPagerState(
        pageCount = {
            tableItems.size
        }
    )

    val eightPagesPerViewport = object : PageSize {
        override fun Density.calculateMainAxisPageSize(
            availableSpace: Int,
            pageSpacing: Int
        ): Int {
            return (availableSpace - 7 * pageSpacing + pageSpacing / 2) / 8
        }
    }

    LaunchedEffect(tableItems) {
        pagerState.apply {
            if (
                (currentPage - (tableItems.find { it.state == ItemState.CURRENT }?.id ?: 0) < -3) &&
                (pageCount - currentPage) != 8
            ) {
                animateScrollToPage(
                    page = currentPage + 1,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }

    HorizontalPager(
        modifier = modifier,
        userScrollEnabled = false,
        state = pagerState,
        pageSpacing = 10.dp,
        pageSize = eightPagesPerViewport
    ) { page ->
        LazyStepItem(tableItems[page])
    }

}

@Composable
fun LazyStepItem(tableItem: TableItem) {

    val stepModifier = Modifier.size(28.dp).then(
        when(tableItem.state) {
            ItemState.FINISHED -> Modifier.border(1.dp, PrimaryColor, CircleShape)
            ItemState.CURRENT -> Modifier.background(PrimaryColor, CircleShape)
            ItemState.DEFAULT -> Modifier.background(DialogBackground, CircleShape)
        }
    )

    val textColor: Color = when(tableItem.state) {
        ItemState.FINISHED -> PrimaryColor
        ItemState.CURRENT -> DefaultBackground
        ItemState.DEFAULT -> PrimaryColor.copy(alpha = 0.6f)
    }

    Box(
        modifier = stepModifier,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = tableItem.id.toString(),
            fontFamily = Poppins,
            fontWeight = FontWeight.Light,
            fontSize = 10.sp,
            color = textColor,
        )
    }
}


@Composable
fun LazyTable(
    modifier: Modifier = Modifier,
    tableItems: List<TableItem>
) {

    var lastFirstVisible by remember {
        mutableIntStateOf(0)
    }

    val listState = rememberLazyListState()

    LaunchedEffect(tableItems) {
        listState.apply {
            if (firstVisibleItemIndex - (tableItems.find { it.state == ItemState.CURRENT }?.id ?: 0) < -3) {
                lastFirstVisible += 1
                animateScrollToItem(lastFirstVisible)
            }
        }
    }

    LazyColumn(
        state = listState,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        content = {
            itemsIndexed(
                items = tableItems,
                key = { _, item ->
                    item.id
                }
            ) { _, item ->
                LazyTableItem(item)
            }
        }
    )
}

@Composable
fun LazyTableItem(tableItem: TableItem) {

    val stepModifier = Modifier.size(28.dp).then(
        when(tableItem.state) {
            ItemState.FINISHED -> Modifier.border(1.dp, PrimaryColor, CircleShape)
            ItemState.CURRENT -> Modifier.background(PrimaryColor, CircleShape)
            ItemState.DEFAULT -> Modifier.background(DialogBackground, CircleShape)
        }
    )

    val statusModifier = Modifier.height(28.dp).fillMaxWidth().then(
        when(tableItem.state) {
            ItemState.FINISHED -> Modifier.border(1.dp, PrimaryColor, RoundedCornerShape(4.dp))
            ItemState.CURRENT -> Modifier.background(PrimaryColor, RoundedCornerShape(4.dp))
            ItemState.DEFAULT -> Modifier.background(DialogBackground, RoundedCornerShape(4.dp))
        }
    )

    val textColor: Color = when(tableItem.state) {
        ItemState.FINISHED -> PrimaryColor
        ItemState.CURRENT -> DefaultBackground
        ItemState.DEFAULT -> PrimaryColor.copy(alpha = 0.6f)
    }

    Row {
        Box(
            modifier = stepModifier,
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = tableItem.id.toString(),
                fontFamily = Poppins,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                color = textColor,
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Box(
            modifier = statusModifier,
            contentAlignment = Alignment.CenterStart,
        ) {
            Text(
                modifier = Modifier.padding(start = 22.dp),
                text = tableItem.status,
                fontFamily = Poppins,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp,
                color = textColor,
            )
        }
    }
}