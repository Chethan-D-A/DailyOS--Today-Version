package com.dailyos.model

import kotlinx.serialization.Serializable

@Serializable
data class ActivityLog(

    val time: String,

    val icon: String,

    val title: String,

    val duration: String = ""

)