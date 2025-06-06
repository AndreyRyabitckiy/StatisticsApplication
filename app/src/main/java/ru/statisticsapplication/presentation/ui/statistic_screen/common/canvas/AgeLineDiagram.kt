package ru.statisticsapplication.presentation.ui.statistic_screen.common.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.presentation.logic.model.ui.AgeDiagramModelUI
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

@Composable
internal fun AgeLineDiagram(
    model: AgeDiagramModelUI,
    modifier: Modifier = Modifier
) {
    val menColor = MaterialTheme.colorScheme.error
    val womenColor = MaterialTheme.colorScheme.errorContainer

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(start = 24.dp, end = 31.dp)
                .width(43.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = model.message
        )

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.padding(top = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(
                        when (model.menPercent) {
                            0 -> 2 / 100f
                            1 -> 2.4f / 100f
                            2 -> 3f / 100f
                            3 -> 3.5f / 100f
                            else -> model.menPercent / 100f
                        }
                    )
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                    ) {
                        drawRoundRect(
                            cornerRadius = CornerRadius(10.dp.toPx()),
                            color = menColor,
                            size = size
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = if (model.menPercent == 100) 16.dp else 24.dp
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    text = "${model.menPercent}%"
                )
                if (model.menPercent < 100) Spacer(modifier = Modifier.weight((100 - model.menPercent) / 100f))
            }

            Spacer(Modifier.height(11.dp))

            Row(
                modifier = Modifier.padding(bottom = 3.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(
                        when (model.womenPercent) {
                            0 -> 2 / 100f
                            1 -> 2.5f / 100f
                            2 -> 3f / 100f
                            3 -> 3.5f / 100f
                            else -> model.womenPercent / 100f
                        }
                    )
                ) {
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                    ) {
                        drawRoundRect(
                            cornerRadius = CornerRadius(10.dp.toPx()),
                            color = womenColor,
                            size = size
                        )
                    }
                }
                Text(
                    modifier = Modifier.padding(
                        start = 10.dp,
                        end = if (model.womenPercent == 100) 16.dp else 26.dp
                    ),
                    style = MaterialTheme.typography.labelMedium,
                    text = "${model.womenPercent}%"
                )
                if (model.womenPercent < 100) Spacer(modifier = Modifier.weight((100 - model.womenPercent) / 100f))
            }
        }
    }
}

@Preview
@Composable
private fun Preview(
) {
    StatisticsApplicationTheme {
        AgeLineDiagram(
            modifier = Modifier.background(Color.White),
            model = AgeDiagramModelUI(
                message = "21-18",
                womenPercent = 50,
                menPercent = 50,
                countMen = 2,
                countWomen = 2,
                ageStart = 18,
                ageEnd = 21
            )
        )
    }
}