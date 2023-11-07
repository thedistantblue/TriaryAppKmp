package com.thedistantblue.triaryapp.theme.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun NameDescriptionScreen(@StringRes nameHint: Int,
                          @StringRes descriptionHint: Int,
                          @StringRes buttonHint: Int,
                          name: String,
                          description: String,
                          onNameChanged: (String) -> Unit,
                          onDescriptionChanged: (String) -> Unit,
                          onClick: (String, String) -> Unit
) {
    Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = name,
                  textStyle = TextStyle.Default.copy(color = Color.White),
                  onValueChange = { onNameChanged(it) },
                  label = { Text(stringResource(nameHint)) })
        Box(modifier = Modifier.height(5.dp))
        TextField(value = description,
                  textStyle = TextStyle.Default.copy(color = Color.White),
                  onValueChange = { onDescriptionChanged(it) },
                  label = { Text(stringResource(descriptionHint)) })
        Box(modifier = Modifier.height(5.dp))
        Button(onClick = {
            onClick(name, description)
        }) {
            Text(text = stringResource(buttonHint))
        }
    }
}