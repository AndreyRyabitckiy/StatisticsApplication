package ru.statisticsapplication.presentation.ui.statistic_screen.common.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.R

@Composable
internal fun GenderRoundDiagram(
    menCount: Int,
    womenCount: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        val total = menCount + womenCount
        if (total == 0) return
        val menPercentage = menCount.toFloat() / total
        val womenPercentage = womenCount.toFloat() / total

        val menColor = MaterialTheme.colorScheme.error
        val womenColor = MaterialTheme.colorScheme.errorContainer

        val gapSize = if (menCount > 0 && womenCount > 0) 8f else 0f
        val gapSizeMin = if (gapSize > 0) gapSize / 2 else 0f

        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(152.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val diameter = size.minDimension

                val menAngle = (360f * menPercentage - gapSizeMin)
                val womenAngle = 360f * womenPercentage - gapSizeMin

                if (womenCount != 0) {
                    drawArc(
                        color = womenColor,
                        startAngle = -90f + gapSizeMin,
                        sweepAngle = womenAngle,
                        useCenter = false,
                        size = Size(diameter, diameter),
                        style = Stroke(
                            width = 8.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                }

                if (menCount != 0) {
                    drawArc(
                        color = menColor,
                        startAngle = -90f + gapSizeMin + womenAngle + gapSize,
                        sweepAngle = menAngle - gapSize,
                        useCenter = false,
                        size = Size(diameter, diameter),
                        style = Stroke(
                            width = 8.dp.toPx(),
                            cap = StrokeCap.Round
                        )
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            LegendGender(
                String.format(
                    stringResource(R.string.men),
                    (menCount * 100 / total).toString()
                ),
                menColor
            )
            LegendGender(
                String.format(
                    stringResource(R.string.women),
                    (100 - menCount * 100 / total).toString()
                ),
                womenColor
            )
        }
    }
}

@Composable
private fun LegendGender(
    text: String,
    color: Color
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(color)
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}