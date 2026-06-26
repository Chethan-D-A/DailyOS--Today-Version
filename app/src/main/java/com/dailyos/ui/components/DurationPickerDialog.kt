package com.dailyos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DurationPickerDialog(
    title: String,
    onDismiss: () -> Unit,
    onDurationSelected: (Int) -> Unit
) {

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text(title)
        },

        text = {

            Column {

                listOf(15, 30, 45, 60).forEach { duration ->

                    TextButton(

                        modifier = Modifier.fillMaxWidth(),

                        onClick = {
                            onDurationSelected(duration)
                        }

                    ) {

                        Text("$duration min")

                    }

                }

            }

        },

        confirmButton = { }

    )

}