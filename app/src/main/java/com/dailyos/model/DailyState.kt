package com.dailyos.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyState(

    val dayState: DayState,

    val goals: List<Goal>,

    val requirements: List<Requirement>,

    val activityLogs: List<ActivityLog>

)