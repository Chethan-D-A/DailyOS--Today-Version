package com.dailyos.model

import kotlinx.serialization.Serializable

@Serializable
data class UserPreferences(

    val sleepGoal: Double = 8.0,

    val waterGoal: Double = 3.0,

    val exerciseGoal: Double = 45.0,

    val outsideGoal: Double = 30.0,

    val familyGoal: Double = 30.0,

    val theme: ThemeMode = ThemeMode.SYSTEM,

    val waterReminder: Boolean = true,

    val exerciseReminder: Boolean = true,

    val outsideReminder: Boolean = true,

    val familyReminder: Boolean = true,

    val sleepReminder: Boolean = true

)