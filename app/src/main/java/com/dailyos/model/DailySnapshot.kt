package com.dailyos.model

data class DailySnapshot(

    val dayState: DayState,

    val goals: List<Goal>,

    val requirements: List<Requirement>,

    val activityLogs: List<ActivityLog>

)
