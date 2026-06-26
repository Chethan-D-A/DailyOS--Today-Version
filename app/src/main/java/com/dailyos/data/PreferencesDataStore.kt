package com.dailyos.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dailyos.model.UserPreferences
import kotlinx.coroutines.flow.first
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val Context.preferencesDataStore by preferencesDataStore(
    name = "preferences"
)

object PreferencesDataStore {

    private val USER_PREFERENCES =
        stringPreferencesKey("user_preferences")

    suspend fun savePreferences(
        context: Context,
        preferences: UserPreferences
    ) {

        val json = Json.encodeToString(
            preferences
        )

        context.preferencesDataStore.edit {

            it[USER_PREFERENCES] = json

        }

    }

    suspend fun loadPreferences(
        context: Context
    ): UserPreferences {

        val json =
            context.preferencesDataStore
                .data
                .first()[USER_PREFERENCES]

        return if (json == null) {

            UserPreferences()

        } else {

            Json.decodeFromString(json)

        }

    }

}