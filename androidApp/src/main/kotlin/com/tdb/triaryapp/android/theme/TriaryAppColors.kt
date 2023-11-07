package com.thedistantblue.triaryapp.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

object TriaryAppColors {
    val triaryAppPrimary = Color(0xFF48CAA0) // Основные кнопки + акцент (типа когда на текстфилд встаешь)
    val triaryAppOnPrimary = Color.Black
    val triaryAppSecondary = triaryAppPrimary // FAB
    val triaryAppOnSecondary = triaryAppOnPrimary
    val triaryAppSurface = Color(0xFF373D46) // Карточка
    val triaryAppOnSurface = Color(0xFFAAAAAA)
    val triaryAppBackground = Color(0xFF212730) // Фон

    val DarkColorScheme = darkColorScheme(
        primary = triaryAppPrimary,
        onPrimary = triaryAppOnPrimary,
        primaryContainer = triaryAppPrimary,
        secondary = triaryAppSecondary,
        onSecondary = triaryAppOnSecondary,
        surface = triaryAppSurface,
        onSurface = triaryAppOnSurface,
        background = triaryAppBackground
    )
}