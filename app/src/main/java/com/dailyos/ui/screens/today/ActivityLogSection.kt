package com.dailyos.ui.screens.today

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dailyos.model.ActivityLog
import com.dailyos.ui.components.SectionHeader

@Composable
fun ActivityLogSection(
    logs: List<ActivityLog>,
    onDelete: (ActivityLog) -> Unit
) {

    SectionHeader("Today's Activity")

    Spacer(modifier = Modifier.height(16.dp))

    if (logs.isEmpty()) {

        Text("No activity logged yet.")

    } else {

        logs.reversed().forEach { log ->

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                val durationText =
                    if (log.duration.isBlank()) ""
                    else "   ${log.duration}"

                Text(
                    text = "${log.time}   ${log.icon} ${log.title}$durationText"
                )

                Text(
                    text = "✕",
                    modifier = Modifier.clickable {
                        onDelete(log)
                    }
                )

            }

            Spacer(modifier = Modifier.height(8.dp))

        }

    }

}