package com.dailyos.util

import com.dailyos.model.DayState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DayManager {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    fun todayString(): String {

        return LocalDate.now().format(formatter)

    }

    fun createToday(): DayState {

        return DayState(

            date = LocalDate.now(),

            sleepHours = 0.0

        )

    }

    fun isNewDay(
        state: DayState
    ): Boolean {

        return state.date != LocalDate.now()

    }

}
