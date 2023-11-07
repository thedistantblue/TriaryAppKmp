package com.thedistantblue.triaryapp.theme.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TriaryAppCard(
    onClickAction: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        onClick = onClickAction,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth().height(100.dp),
        content = content
    )
}