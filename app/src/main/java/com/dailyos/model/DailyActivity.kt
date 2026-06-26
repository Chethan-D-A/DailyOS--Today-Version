package com.dailyos.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyActivity(

    val id: String,

    val icon: String,

    val title: String,

    val usesDuration: Boolean,

    val defaultDuration: Int

)