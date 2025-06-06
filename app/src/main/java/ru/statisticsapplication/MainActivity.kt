package ru.statisticsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.statisticsapplication.presentation.ui.statistic_screen.StatisticsScreen
import ru.statisticsapplication.presentation.ui.theme.StatisticsApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StatisticsApplicationTheme {
                StatisticsScreen()
            }
        }
    }
}
