package com.dailyos.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun SleepDialog(
    show: Boolean,
    onSubmit: (Double) -> Unit
) {

    if (!show) return

    var value by remember {

        mutableStateOf("")

    }

    AlertDialog(

        onDismissRequest = {},

        title = {

            Text("Good Morning")

        },

        text = {

            OutlinedTextField(

                value = value,

                onValueChange = {

                    value = it

                },

                label = {

                    Text("Hours slept")

                },

                keyboardOptions = KeyboardOptions(

                    keyboardType = KeyboardType.Decimal,

                    imeAction = ImeAction.Done

                ),

                singleLine = true

            )

        },

        confirmButton = {

            TextButton(

                onClick = {

                    value.toDoubleOrNull()?.let {

                        onSubmit(it)

                    }

                }

            ) {

                Text("Continue")

            }

        }

    )

}
