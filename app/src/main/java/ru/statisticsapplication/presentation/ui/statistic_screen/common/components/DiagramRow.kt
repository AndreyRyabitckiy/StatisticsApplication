package ru.statisticsapplication.presentation.ui.statistic_screen.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.R

@Composable
internal fun DiagramRow(
    count: Int,
    message: String,
    isPositive: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            modifier = Modifier.padding(start = 20.dp),
            contentDescription = null,
            painter = painterResource(
                if (isPositive) R.drawable.ic_diagram_up else R.drawable.ic_diagram_down
            ),
        )

        Column(
            modifier = Modifier.padding(
                vertical = 16.dp,
                horizontal = 20.dp
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(end = 2.dp),
                    style = MaterialTheme.typography.titleMedium,
                    text = count.toString()
                )

                Image(
                    contentDescription = null,
                    painter = painterResource(
                        if (isPositive) R.drawable.ic_up_16 else R.drawable.ic_down_16
                    ),
                )
            }

            Text(
                modifier = Modifier.padding(top = 7.dp),
                style = MaterialTheme.typography.bodyLarge,
                text = message
            )
        }
    }
}