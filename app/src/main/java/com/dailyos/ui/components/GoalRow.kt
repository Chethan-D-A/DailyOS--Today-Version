package com.dailyos.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GoalRow(
    title: String,
    completed: Boolean,
    onToggle: () -> Unit,
    onDelete: () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "${if (completed) "☑" else "☐"}  $title",
            modifier = Modifier
                .weight(1f)
                .clickable {
                    onToggle()
                }
                .padding(vertical = 8.dp)
        )

        Text(
            text = "✕",
            modifier = Modifier
                .clickable {
                    onDelete()
                }
                .padding(8.dp)
        )

    }

}