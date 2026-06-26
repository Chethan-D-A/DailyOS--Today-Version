package com.dailyos.ui.screens.today

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dailyos.model.DailyActivity
import com.dailyos.viewmodel.TodayViewModel
import androidx.compose.ui.platform.LocalContext
import com.dailyos.model.Goal
import com.dailyos.model.ActivityLog
import com.dailyos.ui.components.ConfirmationDialog
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets

@Composable
fun TodayScreen(

    innerPadding: PaddingValues

){

    val context = LocalContext.current

    val vm: TodayViewModel = viewModel()

    LaunchedEffect(Unit) {

        vm.initializeDay()

    }

    var showGoalDialog by remember {

        mutableStateOf(false)

    }

    var newGoal by remember {

        mutableStateOf("")

    }

    var selectedActivity by remember {

        mutableStateOf<DailyActivity?>(null)

    }

    var goalToDelete by remember {

        mutableStateOf<Goal?>(null)

    }

    var activityToDelete by remember {

        mutableStateOf<ActivityLog?>(null)

    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
            .consumeWindowInsets(innerPadding)
            .padding(horizontal = 20.dp)
            .padding(top = 12.dp),

        verticalArrangement = Arrangement.Top

    ) {

        Text(

            text = "TODAY",

            fontSize = 28.sp,

            fontWeight = FontWeight.Bold

        )


        Spacer(modifier = Modifier.height(28.dp))

        RequirementsSection(
            requirements = vm.requirements
        )

        GoalsSection(

            goals = vm.goals,

            onGoalToggle = vm::toggleGoal,

            onGoalDelete = {

                goalToDelete = it

            },

            onAddGoal = {

                showGoalDialog = true

            }

        )

        QuickLogSection(

            activities = vm.activities,

            onActivityClick = {

                selectedActivity = it

            }

        )

        ActivityLogSection(

            logs = vm.activityLogs,

            onDelete = {

                activityToDelete = it

            }

        )

    }

    GoalDialog(

        show = showGoalDialog,

        value = newGoal,

        onValueChange = {

            newGoal = it

        },

        onAdd = {

            vm.addGoal(newGoal)

            newGoal = ""

            showGoalDialog = false

        },

        onDismiss = {

            showGoalDialog = false

            newGoal = ""

        }

    )

    ActivityDialog(

        activity = selectedActivity,

        onDismiss = {

            selectedActivity = null

        },

        onDurationSelected = { minutes ->

            vm.logActivity(

                selectedActivity!!,

                minutes

            )

            selectedActivity = null

        },

        onInstantLog = {

            vm.logActivity(

                selectedActivity!!,

                0

            )

            selectedActivity = null

        }

    )


    ConfirmationDialog(

        show = goalToDelete != null,

        title = "Delete Goal",

        message = "Are you sure you want to delete this goal?",

        onConfirm = {

            vm.deleteGoal(

                goalToDelete!!

            )

            goalToDelete = null

        },

        onDismiss = {

            goalToDelete = null

        }

    )

    ConfirmationDialog(

        show = activityToDelete != null,

        title = "Delete Activity",

        message = "Are you sure you want to delete this activity?",

        onConfirm = {

            vm.deleteActivity(

                activityToDelete!!

            )

            activityToDelete = null

        },

        onDismiss = {

            activityToDelete = null

        }

    )

}
