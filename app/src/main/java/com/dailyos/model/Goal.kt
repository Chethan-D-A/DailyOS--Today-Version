package com.dailyos.model

import kotlinx.serialization.Serializable

@Serializable
data class Goal(

    val title: String,

    val completed: Boolean = false

)