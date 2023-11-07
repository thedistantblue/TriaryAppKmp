package com.thedistantblue.triaryapp.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TriaryAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = TriaryAppColors.DarkColorScheme,
        content = content
    )
}