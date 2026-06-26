package com.dailyos.ui.screens.today

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.ImeAction
import com.dailyos.model.DailyActivity
import com.dailyos.ui.components.DurationPickerDialog

@Composable
fun GoalDialog(
    show: Boolean,
    value: String,
    onValueChange: (String) -> Unit,
    onAdd: () -> Unit,
    onDismiss: () -> Unit
) {

    if (!show) return

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text("New Goal")
        },

        text = {

            OutlinedTextField(

                value = value,

                onValueChange = onValueChange,

                singleLine = true,

                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )

            )

        },

        confirmButton = {

            TextButton(onClick = onAdd) {
                Text("Add")
            }

        },

        dismissButton = {

            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }

        }

    )

}

@Composable
fun ActivityDialog(
    activity: DailyActivity?,
    onDismiss: () -> Unit,
    onDurationSelected: (Int) -> Unit,
    onInstantLog: () -> Unit
) {

    activity ?: return

    if (activity.usesDuration) {

        DurationPickerDialog(
            title = activity.title,
            onDismiss = onDismiss,
            onDurationSelected = onDurationSelected
        )

    } else {

        AlertDialog(

            onDismissRequest = onDismiss,

            title = {
                Text(activity.title)
            },

            text = {
                Text("Log this activity?")
            },

            confirmButton = {

                TextButton(onClick = onInstantLog) {
                    Text("Log")
                }

            },

            dismissButton = {

                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }

            }

        )

    }

}
