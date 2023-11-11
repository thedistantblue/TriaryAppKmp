package com.tdb.triaryapp.android.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun TextRowWithRightArrow(textString: String, onClickAction: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable(onClick = onClickAction)) {
        ConstraintLayout(Modifier.fillMaxWidth().padding(5.dp)) {
            val (text, icon) = createRefs()
            Text(
                text = textString,
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start)
                })
            Icon(
                Icons.Filled.ArrowForward, "",
                modifier = Modifier.constrainAs(icon) {
                    end.linkTo(parent.end)
                })
        }
    }
}