package ru.statisticsapplication.presentation.ui.statistic_screen.common.blocks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.statisticsapplication.R
import ru.statisticsapplication.presentation.logic.model.ui.UserUI
import ru.statisticsapplication.presentation.ui.statistic_screen.common.items.UserItem
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

@Composable
internal fun FrequencyOfVisits(
    list: List<UserUI>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.most_visits)
        )

        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
        ) {
            list.forEach {
                UserItem(it)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    StatisticsApplicationTheme {
        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            FrequencyOfVisits(
                list = listOf(
                    UserUI(
                        id = 1,
                        sex = "",
                        userName = "Ivan",
                        isOnline = true,
                        age = 25,
                        avatar = ""
                    ),
                    UserUI(
                        id = 1,
                        sex = "",
                        userName = "Alexander",
                        isOnline = false,
                        age = 37,
                        avatar = ""
                    ),
                    UserUI(
                        id = 1,
                        sex = "",
                        userName = "Petr",
                        isOnline = true,
                        age = 19,
                        avatar = ""
                    )
                )
            )
        }
    }
}