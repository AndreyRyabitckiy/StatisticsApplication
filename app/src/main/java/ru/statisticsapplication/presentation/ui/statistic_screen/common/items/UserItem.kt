package ru.statisticsapplication.presentation.ui.statistic_screen.common.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W600
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ru.statisticsapplication.R
import ru.statisticsapplication.presentation.logic.model.ui.UserUI
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

@Composable
internal fun UserItem(
    userUI: UserUI,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Box(
            modifier = Modifier
                .padding(
                    top = 12.dp,
                    bottom = 12.dp,
                    end = 12.dp,
                    start = 16.dp
                )
        ) {
            AsyncImage(
                model = userUI.avatar,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape),
                error = ColorPainter(MaterialTheme.colorScheme.background),
                placeholder = ColorPainter(MaterialTheme.colorScheme.background)
            )

            if (userUI.isOnline) Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.onError,
                        shape = CircleShape
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    )
            )
        }

        Text(
            maxLines = 1,
            fontSize = 15.sp,
            fontWeight = W600,
            text = userUI.userName + ", " + userUI.age.toString()
        )

        Spacer(Modifier.weight(1f))

        Image(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(24.dp),
            painter = painterResource(R.drawable.ic_arrow_right_24),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun Preview() {
    StatisticsApplicationTheme {
        Column {
            UserItem(
                userUI = UserUI(
                    id = 1,
                    sex = "",
                    userName = "Ivan",
                    isOnline = true,
                    age = 22,
                    avatar = ""
                )
            )
        }
    }
}