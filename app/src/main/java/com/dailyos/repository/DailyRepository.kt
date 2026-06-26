package com.dailyos.repository

import android.content.Context
import com.dailyos.data.DailyDataStore
import com.dailyos.model.ActivityLog
import com.dailyos.model.Goal

class DailyRepository(
    private val context: Context
) {

    // Goals

    suspend fun saveGoals(goals: List<Goal>) =
        DailyDataStore.saveGoals(context, goals)

    suspend fun loadGoals() =
        DailyDataStore.loadGoals(context)

    // Activity Logs

    suspend fun saveActivityLogs(logs: List<ActivityLog>) =
        DailyDataStore.saveActivityLogs(context, logs)

    suspend fun loadActivityLogs() =
        DailyDataStore.loadActivityLogs(context)

    // Sleep

    suspend fun saveSleep(hours: Double) =
        DailyDataStore.saveSleep(context, hours)

    suspend fun loadSleep() =
        DailyDataStore.loadSleep(context)

    // Day

    suspend fun saveLastOpenDate(date: String) =
        DailyDataStore.saveLastOpenDate(context, date)

    suspend fun loadLastOpenDate() =
        DailyDataStore.loadLastOpenDate(context)

}