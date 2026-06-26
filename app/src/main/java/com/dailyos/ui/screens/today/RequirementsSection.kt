package com.dailyos.ui.screens.today

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dailyos.engine.RequirementEngine
import com.dailyos.model.Requirement
import com.dailyos.ui.components.RequirementRow
import com.dailyos.ui.components.SectionHeader

@Composable
fun RequirementsSection(
    requirements: List<Requirement>
) {

    val percentage =
        RequirementEngine.completionPercentage(requirements)

    val filled = (percentage / 10).coerceIn(0, 10)

    val bar =
        "█".repeat(filled) +
                "░".repeat(10 - filled)

    SectionHeader("Requirements")

    Spacer(modifier = Modifier.height(8.dp))

    Text("$bar $percentage%")

    Spacer(modifier = Modifier.height(16.dp))

    requirements.forEach { requirement ->

        RequirementRow(

            name = requirement.name,

            progress = "${requirement.current}/${requirement.target} ${requirement.unit}"

        )

    }

    Spacer(modifier = Modifier.height(24.dp))

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(24.dp))

}