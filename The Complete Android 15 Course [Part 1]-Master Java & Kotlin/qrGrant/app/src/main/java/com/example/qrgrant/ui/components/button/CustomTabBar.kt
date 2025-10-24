package com.example.qrgrant.ui.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.qrgrant.R
import com.example.qrgrant.ui.theme.PrimaryStyle
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTabBar(
    titles: List<String>,
    contents: List<@Composable () -> Unit>,
    textColor: Color = colorResource(R.color.primary),
    containerColor: Color = Color.White,
    indicatorColor: Color = colorResource(R.color.primary),
    selectedTabBackground: Color = Color.LightGray.copy(alpha = 0.3f)
) {
    val pagerState = rememberPagerState(pageCount = { titles.size })
    val scope = rememberCoroutineScope()

    Column {
        PrimaryTabRow(selectedTabIndex = pagerState.currentPage,
            containerColor = containerColor,
            indicator = {
                Box(modifier = Modifier
                    .tabIndicatorLayout { measurable, constraints, tabPositions ->
                        val page = pagerState.currentPage
                        val offset = pagerState.currentPageOffsetFraction.coerceIn(-1f, 1f)
                        val nextPage = (page + if (offset > 0) 1 else -1).coerceIn(
                            0, tabPositions.lastIndex
                        )

                        val cur = tabPositions[page]
                        val nxt = tabPositions[nextPage]

                        val leftDp = lerp(cur.left, nxt.left, offset.absoluteValue)
                        val rightDp = lerp(cur.right, nxt.right, offset.absoluteValue)

                        val widthPx = (rightDp - leftDp)
                            .roundToPx()
                            .coerceAtLeast(1)
                        val heightPx = 50.dp.roundToPx()

                        val placeable = measurable.measure(
                            androidx.compose.ui.unit.Constraints.fixed(widthPx, heightPx)
                        )
                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.placeRelative(
                                leftDp.roundToPx(), (constraints.maxHeight - heightPx) / 2
                            )
                        }
                    }
                    .height(50.dp)
                    .background(selectedTabBackground))

                titles.forEachIndexed { i, _ ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier
                            .tabIndicatorOffset(i)
                            .height(4.dp),
                        color = Color.Gray.copy(.6f)
                    )
                }


                Box(modifier = Modifier
                    .tabIndicatorLayout { measurable, constraints, tabPositions ->
                        val page = pagerState.currentPage
                        val offset = pagerState.currentPageOffsetFraction.coerceIn(-1f, 1f)
                        val nextPage = (page + if (offset > 0) 1 else -1).coerceIn(
                            0, tabPositions.lastIndex
                        )

                        val cur = tabPositions[page]
                        val nxt = tabPositions[nextPage]

                        val leftDp = lerp(cur.left, nxt.left, offset.absoluteValue)
                        val rightDp = lerp(cur.right, nxt.right, offset.absoluteValue)

                        val widthPx = (rightDp - leftDp)
                            .roundToPx()
                            .coerceAtLeast(1)
                        val heightPx = 4.dp.roundToPx()

                        val placeable = measurable.measure(
                            androidx.compose.ui.unit.Constraints.fixed(widthPx, heightPx)
                        )
                        layout(constraints.maxWidth, constraints.maxHeight) {
                            placeable.placeRelative(
                                leftDp.roundToPx(), constraints.maxHeight - heightPx
                            )
                        }
                    }
                    .height(4.dp)
                    .background(indicatorColor))
            }) {

            titles.forEachIndexed { index, title ->
                val selected = pagerState.currentPage == index
                Tab(selected = selected,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = {
                        Text(
                            text = title,
                            style = PrimaryStyle.regular(18, textColor),
                        )
                    })
            }
        }

        HorizontalPager(state = pagerState) { page ->
            Box(Modifier.fillMaxSize()) {
                contents[page]()
            }
        }
    }
}
