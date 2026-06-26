package com.dailyos.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ConfirmationDialog(

    show: Boolean,

    title: String,

    message: String,

    onConfirm: () -> Unit,

    onDismiss: () -> Unit

) {

    if (!show)
        return

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text(title)

        },

        text = {

            Text(message)

        },

        confirmButton = {

            Button(

                onClick = onConfirm

            ) {

                Text("Delete")

            }

        },

        dismissButton = {

            Button(

                onClick = onDismiss

            ) {

                Text("Cancel")

            }

        }

    )

}