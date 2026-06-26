package com.dailyos.model

import kotlinx.serialization.Serializable

@Serializable
data class Requirement(

    val name: String,

    val unit: String,

    val target: Double,

    val current: Double

)