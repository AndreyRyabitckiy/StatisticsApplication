package ru.statisticsapplication.presentation.ui.statistic_screen.common.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.statisticsapplication.presentation.logic.model.ui.VisitorsAndDateModelUI
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

@Composable
internal fun VisitorsDiagram(
    date: List<VisitorsAndDateModelUI>
) {
    val textMeasurer = rememberTextMeasurer()
    var selectedItem by remember { mutableStateOf<VisitorsAndDateModelUI?>(null) }

    val redColor = MaterialTheme.colorScheme.error
    val whiteColor = MaterialTheme.colorScheme.primaryContainer
    val darkGreyBorderColor = MaterialTheme.colorScheme.onSecondary
    val darkGreyColor = MaterialTheme.colorScheme.secondary

    Column(
        Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Canvas(
            modifier = Modifier
                .height(208.dp)
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    end = 32.dp
                )
                .pointerInput(date) {
                    detectTapGestures { offset ->

                        val sectorLineX =
                            ((size.width - 12.dp.toPx()) / date.size - (10.dp.toPx() / date.size - 1))
                        val sectorLineY =
                            (size.height - 125.dp.toPx()) / (date.maxByOrNull { it.count }?.count
                                ?: 1f).toFloat()

                        date.forEachIndexed { index, item ->
                            val circleCenter = Offset(
                                sectorLineX * index + sectorLineX / 2,
                                size.height - 33.dp.toPx() - sectorLineY * item.count
                            )

                            val circleRadius = 8.dp.toPx()
                            if (offset.x in (circleCenter.x - circleRadius)..(circleCenter.x + circleRadius) &&
                                offset.y in (circleCenter.y - circleRadius)..(circleCenter.y + circleRadius)
                            ) {
                                selectedItem = if (selectedItem != item) item else null
                            }
                        }
                    }
                }
        ) {
            val sectorLineX =
                ((size.width - 12.dp.toPx()) / date.size - (10.dp.toPx() / date.size - 1))
            val sectorLineY = (size.height - 125.dp.toPx()) / (date.maxByOrNull { it.count }?.count
                ?: 1f).toFloat()

            drawLine(
                color = darkGreyColor,
                start = Offset(0f, 29.dp.toPx()),
                end = Offset(size.width, 29.dp.toPx()),
                strokeWidth = 1.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(20f, 10f),
                    phase = 0f
                )
            )


            drawLine(
                color = darkGreyColor,
                start = Offset(0f, 101.dp.toPx()),
                end = Offset(size.width, 101.dp.toPx()),
                strokeWidth = 1.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(20f, 10f),
                    phase = 0f
                )
            )

            drawLine(
                color = darkGreyColor,
                start = Offset(0f, 176.dp.toPx()),
                end = Offset(size.width, 176.dp.toPx()),
                strokeWidth = 1.dp.toPx(),
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(20f, 10f),
                    phase = 0f
                )
            )

            date.forEachIndexed { index, item ->
                val offSetRound = Offset(
                    sectorLineX * (index) + sectorLineX / 2,
                    size.height - 33.dp.toPx() - sectorLineY * item.count
                )

                drawText(
                    style = TextStyle(
                        fontSize = 11.sp,
                        color = darkGreyColor,
                        fontWeight = FontWeight.Normal
                    ),
                    text = item.downMessage,
                    textMeasurer = textMeasurer,
                    topLeft = Offset(
                        sectorLineX * (index) + sectorLineX / 2 - 18.dp.toPx(),
                        size.height - 20.dp.toPx()
                    )
                )

                if (index < date.size - 1) {
                    drawLine(
                        start = offSetRound,
                        end = Offset(
                            sectorLineX * (index + 1) + sectorLineX / 2,
                            size.height - 33.dp.toPx() - sectorLineY * date[index + 1].count
                        ),
                        color = redColor,
                        strokeWidth = 4.dp.toPx()
                    )
                }

                drawCircle(
                    center = offSetRound,
                    color = whiteColor,
                    radius = 5.dp.toPx()
                )
                drawCircle(
                    center = offSetRound,
                    color = redColor,
                    style = Stroke(
                        width = 3.dp.toPx()
                    ),
                    radius = 5.dp.toPx()
                )
            }

            selectedItem?.let { item ->
                val index = date.indexOf(item)
                if (index != -1) {
                    val xPos = sectorLineX * index + sectorLineX / 2
                    drawLine(
                        color = redColor,
                        start = Offset(xPos, 9.dp.toPx()),
                        end = Offset(xPos, size.height - 35.dp.toPx()),
                        strokeWidth = 1.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(20f, 10f),
                            phase = 0f
                        )
                    )

                    val rectWidth = 128.dp.toPx()
                    val rectHeight = 72.dp.toPx()
                    val x = xPos - rectWidth / 2
                    val xOffset = if (x < 0 ) {
                        0f
                    } else if (x + rectWidth > size.width) {
                        size.width - rectWidth
                    } else {
                        x
                    }

                    drawRoundRect(
                        color = whiteColor,
                        topLeft = Offset(
                            x = xOffset,
                            y = 9.dp.toPx()
                        ),
                        size = Size(rectWidth, rectHeight),
                        cornerRadius = CornerRadius(12.dp.toPx())
                    )

                    drawRoundRect(
                        color = darkGreyBorderColor,
                        topLeft = Offset(xOffset, 9.dp.toPx()),
                        size = Size(rectWidth, rectHeight),
                        cornerRadius = CornerRadius(12.dp.toPx()),
                        style = Stroke(width = 1.dp.toPx())
                    )

                    drawText(
                        textMeasurer = textMeasurer,
                        text = item.countMessage,
                        style = TextStyle(
                            color = redColor,
                            fontSize = 15.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        size = Size(rectWidth, rectHeight),
                        topLeft = Offset(
                            xOffset + 16.dp.toPx(),
                            25.dp.toPx()
                        )
                    )

                    drawText(
                        textMeasurer = textMeasurer,
                        text = item.tapMessage,
                        style = TextStyle(
                            color = darkGreyColor,
                            fontSize = 15.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        ),
                        size = Size(rectWidth, rectHeight),
                        topLeft = Offset(
                            xOffset + 16.dp.toPx(),
                            49.dp.toPx()
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StatisticsApplicationTheme {
        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            VisitorsDiagram(
                listOf(
                    VisitorsAndDateModelUI(
                        count = 0,
                        downMessage = "06.11",
                        tapMessage = "6 декабря",
                        countMessage = ""
                    ),

                    VisitorsAndDateModelUI(
                        count = 1,
                        downMessage = "07.11",
                        tapMessage = "7 декабря",
                        countMessage = ""
                    ),

                    VisitorsAndDateModelUI(
                        count = 18,
                        downMessage = "08.11",
                        tapMessage = "8 декабря",
                        countMessage = ""
                    ),

                    VisitorsAndDateModelUI(
                        count = 6,
                        downMessage = "09.11",
                        tapMessage = "9 декабря",
                        countMessage = ""
                    ),

                    VisitorsAndDateModelUI(
                        count = 7,
                        downMessage = "10.11",
                        tapMessage = "10 декабря",
                        countMessage = ""
                    ),

                    VisitorsAndDateModelUI(
                        count = 8,
                        downMessage = "11.11",
                        tapMessage = "11 декабря",
                        countMessage = ""
                    ),

                    VisitorsAndDateModelUI(
                        count = 9,
                        downMessage = "12.11",
                        tapMessage = "12 декабря",
                        countMessage = ""
                    ),
                )
            )
        }
    }
}