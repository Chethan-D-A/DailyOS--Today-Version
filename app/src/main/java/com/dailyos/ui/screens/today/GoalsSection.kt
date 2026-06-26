package com.dailyos.ui.screens.today

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dailyos.model.Goal
import com.dailyos.ui.components.GoalRow
import com.dailyos.ui.components.SectionHeaderWithAction

@Composable
fun GoalsSection(
    goals: MutableList<Goal>,
    onGoalToggle: (Goal) -> Unit,
    onGoalDelete: (Goal) -> Unit,
    onAddGoal: () -> Unit
) {

    SectionHeaderWithAction(
        title = "Today's Goals",
        actionText = "+"
    ) {
        onAddGoal()
    }

    Spacer(modifier = Modifier.height(16.dp))

    if (goals.isEmpty()) {

        androidx.compose.material3.Text(
            "No goals for today."
        )

    } else {

        goals.forEach { goal ->

            GoalRow(

                title = goal.title,

                completed = goal.completed,

                onToggle = {

                    onGoalToggle(goal)

                },

                onDelete = {

                    onGoalDelete(goal)

                }

            )

        }

    }

    Spacer(modifier = Modifier.height(24.dp))

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(24.dp))

}