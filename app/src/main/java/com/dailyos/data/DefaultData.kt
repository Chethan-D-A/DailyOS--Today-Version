package com.dailyos.data

import com.dailyos.model.DailyActivity
import com.dailyos.model.Goal
import com.dailyos.model.Requirement

object DefaultData {

    fun goals() = mutableListOf(

        Goal("Meditate"),

        Goal("Morning Walk"),

        Goal("Workout")

    )

    fun requirements() = mutableListOf(

        Requirement("Sleep", "h", 7.0, 0.0),

        Requirement("Meals", "", 3.0, 0.0),

        Requirement("Water", "L", 3.0, 0.0),

        Requirement("Exercise", "min", 45.0, 0.0),

        Requirement("Outside", "min", 45.0, 0.0),

        Requirement("Family", "min", 60.0, 0.0)

    )

    fun activities() = listOf(

        DailyActivity("walk", "🚶", "Walk", true, 30),

        DailyActivity("workout", "🏋", "Workout", true, 45),

        DailyActivity("meal", "🍽", "Meal", false, 0),

        DailyActivity("water", "💧", "Water", false, 0),

        DailyActivity("meditation", "🧘", "Meditation", true, 15),

        DailyActivity("family", "👨‍👩‍👦", "Family", true, 30),

        DailyActivity("study", "📚", "Study", true, 60),

        DailyActivity("work", "💻", "Work", true, 60)

    )

}