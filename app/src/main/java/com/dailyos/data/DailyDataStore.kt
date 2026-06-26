package com.dailyos.data

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dailyos.model.Goal
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.dailyos.model.ActivityLog

private val Context.dataStore by preferencesDataStore(
    name = "dailyos"
)

object DailyDataStore {

    private val DATE =
        stringPreferencesKey("date")

    private val SLEEP =
        doublePreferencesKey("sleep")

    private val LAST_OPEN_DATE =
        stringPreferencesKey("last_open_date")


    private val GOALS =
        stringPreferencesKey("goals")

    private val ACTIVITY_LOGS =
        stringPreferencesKey("activity_logs")

    suspend fun saveGoals(
        context: Context,
        goals: List<Goal>
    ) {

        val json = Json.encodeToString(goals)

        context.dataStore.edit {

            it[GOALS] = json

        }

    }

    suspend fun loadGoals(
        context: Context
    ): List<Goal> {

        val json =
            context.dataStore.data
                .first()[GOALS]
                ?: return emptyList()

        return Json.decodeFromString(json)

    }

    suspend fun saveLastOpenDate(
        context: Context,
        date: String
    ) {

        context.dataStore.edit {

            it[LAST_OPEN_DATE] = date

        }

    }

    suspend fun loadLastOpenDate(
        context: Context
    ): String? {

        return context.dataStore.data
            .first()[LAST_OPEN_DATE]

    }

    suspend fun saveActivityLogs(
        context: Context,
        logs: List<ActivityLog>
    ) {

        val json = Json.encodeToString(logs)

        context.dataStore.edit {

            it[ACTIVITY_LOGS] = json

        }

    }

    suspend fun loadActivityLogs(
        context: Context
    ): List<ActivityLog> {

        val json =
            context.dataStore.data
                .first()[ACTIVITY_LOGS]
                ?: return emptyList()

        return Json.decodeFromString(json)

    }

    suspend fun saveDate(
        context: Context,
        date: String
    ) {

        context.dataStore.edit {

            it[DATE] = date

        }

    }

    suspend fun loadDate(
        context: Context
    ): String? {

        return context.dataStore.data
            .first()[DATE]

    }

    suspend fun saveSleep(
        context: Context,
        hours: Double
    ) {

        context.dataStore.edit {

            it[SLEEP] = hours

        }

    }

    suspend fun loadSleep(
        context: Context
    ): Double {

        return context.dataStore.data
            .first()[SLEEP] ?: 0.0

    }

}