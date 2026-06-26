package com.dailyos.ui.screens.today

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dailyos.model.DailyActivity
import com.dailyos.ui.components.QuickLogItem
import com.dailyos.ui.components.SectionHeader

@Composable
fun QuickLogSection(
    activities: List<DailyActivity>,
    onActivityClick: (DailyActivity) -> Unit
) {

    SectionHeader("Quick Log")

    Spacer(modifier = Modifier.height(16.dp))

    activities.forEach { activity ->

        QuickLogItem(

            icon = activity.icon,

            title = activity.title

        ) {

            onActivityClick(activity)

        }

    }

    Spacer(modifier = Modifier.height(24.dp))

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(24.dp))

}