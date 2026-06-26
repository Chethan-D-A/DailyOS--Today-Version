package com.dailyos.ui.screens.preferences

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NumberInputDialog(

    show: Boolean,

    title: String,

    value: Double,

    unit: String,

    onDismiss: () -> Unit,

    onSave: (Double) -> Unit

) {

    if (!show)
        return

    var text by remember {

        mutableStateOf(
            value.toString()
        )

    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text(title)

        },

        text = {

            OutlinedTextField(

                value = text,

                onValueChange = {

                    text = it

                },

                suffix = {

                    Text(unit)

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType =
                        KeyboardType.Decimal

                )

            )

        },

        confirmButton = {

            Button(

                onClick = {

                    text.toDoubleOrNull()?.let {

                        onSave(it)

                    }

                }

            ) {

                Text("Save")

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