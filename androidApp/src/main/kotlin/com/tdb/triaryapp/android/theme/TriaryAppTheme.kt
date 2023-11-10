package com.tdb.triaryapp.android.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.tdb.triaryapp.android.theme.TriaryAppColors

@Composable
fun TriaryAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = TriaryAppColors.DarkColorScheme,
        content = content
    )
}