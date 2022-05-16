package com.ilhomsoliev.timerandstopwatch.app.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.common_ui.theme.Red

@Composable
fun TopAppBar(text: String) {
    androidx.compose.material.TopAppBar(
        backgroundColor = Red
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier.size(27.dp)
            )
            Text(text = text, color = Color.White, fontSize = 21.sp)
        }
    }
}
