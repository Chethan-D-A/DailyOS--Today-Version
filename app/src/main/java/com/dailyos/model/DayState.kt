package com.dailyos.model

import com.dailyos.serialization.LocalDateSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import java.time.LocalDate

@Serializable
data class DayState(

    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,

    val sleepHours: Double = 0.0,


)