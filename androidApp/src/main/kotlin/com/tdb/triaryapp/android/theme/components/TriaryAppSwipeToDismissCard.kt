package com.thedistantblue.triaryapp.theme.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.scale
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriaryAppSwipeToDismissCard(
        onClickAction: () -> Unit,
        onDismissedToEndAction: (DismissState) -> Unit,
        onDismissedToStartAction: (DismissState) -> Unit,
        content: @Composable ColumnScope.() -> Unit
) {
    val dismissState = rememberDismissState()
    val coroutineScope = rememberCoroutineScope()

    SwipeToDismiss(
        state = dismissState,
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss

            val color by animateColorAsState(
                targetValue = when (dismissState.targetValue) {
                    DismissValue.Default -> Color.LightGray
                    DismissValue.DismissedToEnd -> Color.Green
                    DismissValue.DismissedToStart -> Color.Red
                }
            )

            val icon = when (direction) {
                DismissDirection.StartToEnd -> Icons.Default.Done
                DismissDirection.EndToStart -> Icons.Default.Delete
            }

            val scale by animateFloatAsState(
                targetValue = if (dismissState.targetValue == DismissValue.Default)
                    0.8f else 1.2f
            )

            if (dismissState.currentValue == DismissValue.DismissedToStart) {
                onDismissedToStartAction.invoke(dismissState)
            }

            if (dismissState.currentValue == DismissValue.DismissedToEnd) {
                onDismissedToEndAction.invoke(dismissState)
                //val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                //val vib = vibratorManager.defaultVibrator
                //vib.vibrate(VibrationEffect.createOneShot(100, 100))
                coroutineScope.launch {
                    //dismissState.animateTo(DismissValue.Default)
                    dismissState.snapTo(DismissValue.Default)
                }
            }

            val alignment = when (direction) {
                DismissDirection.EndToStart -> Alignment.CenterEnd
                DismissDirection.StartToEnd -> Alignment.CenterStart
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(start = 12.dp, end = 12.dp),
                contentAlignment = alignment
            ) {
                Icon(icon, contentDescription = "icon", modifier = Modifier.scale(scale))
            }
        },
        dismissContent = {
            TriaryAppCard(onClickAction = onClickAction, content = content)
        }
    )
}