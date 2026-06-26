package com.dailyos.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DailyOSColorScheme = lightColorScheme(

    primary = Purple40,

    secondary = PurpleGrey40,

    tertiary = Pink40

)

@Composable
fun DailyOSTheme(

    content: @Composable () -> Unit

) {

    MaterialTheme(

        colorScheme = DailyOSColorScheme,

        typography = Typography,

        content = content

    )

}