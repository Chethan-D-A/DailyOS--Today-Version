package com.dailyos.ui.screens.preferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dailyos.viewmodel.PreferencesViewModel
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.runtime.LaunchedEffect

@Composable
fun PreferencesScreen(

    innerPadding: PaddingValues

) {

    val vm: PreferencesViewModel = viewModel()

    val prefs = vm.preferences

    var todaySleep by remember {

        mutableStateOf(0.0)

    }

    LaunchedEffect(Unit) {

        vm.loadTodaySleep {

            todaySleep = it

        }

    }

    var dialogTitle by remember { mutableStateOf("") }
    var dialogUnit by remember { mutableStateOf("") }
    var dialogValue by remember { mutableStateOf(0.0) }
    var showDialog by remember { mutableStateOf(false) }
    var onSave by remember { mutableStateOf<(Double) -> Unit>({}) }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(
                rememberScrollState()
            )
            .padding(innerPadding)
            .consumeWindowInsets(innerPadding),

        verticalArrangement = Arrangement.Top

    ) {

        Text(

            text = "PREFERENCES",

            fontSize = 28.sp,

            fontWeight = FontWeight.Bold

        )

        Card(

            modifier = Modifier.padding(top = 24.dp)

        ) {

            PreferenceRow(

                title = "Sleep Goal",

                value = "${prefs.sleepGoal} h",

                onClick = {

                    dialogTitle = "Sleep Goal"
                    dialogUnit = "h"
                    dialogValue = prefs.sleepGoal
                    onSave = vm::updateSleepGoal
                    showDialog = true

                }

            )

            PreferenceRow(

                title = "Water Goal",

                value = "${prefs.waterGoal} L",

                onClick = {

                    dialogTitle = "Water Goal"
                    dialogUnit = "L"
                    dialogValue = prefs.waterGoal
                    onSave = vm::updateWaterGoal
                    showDialog = true

                }

            )

            PreferenceRow(

                title = "Exercise Goal",

                value = "${prefs.exerciseGoal} min",

                onClick = {

                    dialogTitle = "Exercise Goal"
                    dialogUnit = "min"
                    dialogValue = prefs.exerciseGoal
                    onSave = vm::updateExerciseGoal
                    showDialog = true

                }

            )

            PreferenceRow(

                title = "Outside Goal",

                value = "${prefs.outsideGoal} min",

                onClick = {

                    dialogTitle = "Outside Goal"
                    dialogUnit = "min"
                    dialogValue = prefs.outsideGoal
                    onSave = vm::updateOutsideGoal
                    showDialog = true

                }

            )

            PreferenceRow(

                title = "Family Goal",

                value = "${prefs.familyGoal} min",

                onClick = {

                    dialogTitle = "Family Goal"
                    dialogUnit = "min"
                    dialogValue = prefs.familyGoal
                    onSave = vm::updateFamilyGoal
                    showDialog = true

                }

            )

            PreferenceRow(

                title = "Today's Sleep",

                value = "${todaySleep} h",

                onClick = {

                    dialogTitle = "Today's Sleep"

                    dialogUnit = "h"

                    dialogValue = todaySleep

                    onSave = {

                        vm.updateTodaySleep(it)

                        todaySleep = it

                    }

                    showDialog = true

                }

            )

        }

    }

    NumberInputDialog(

        show = showDialog,

        title = dialogTitle,

        value = dialogValue,

        unit = dialogUnit,

        onDismiss = {

            showDialog = false

        },

        onSave = {

            onSave(it)

            showDialog = false

        }

    )

}