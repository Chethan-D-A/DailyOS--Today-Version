package com.dailyos.repository

import android.content.Context
import com.dailyos.data.PreferencesDataStore
import com.dailyos.model.UserPreferences

class PreferencesRepository(
    private val context: Context
) {

    suspend fun savePreferences(
        preferences: UserPreferences
    ) {

        PreferencesDataStore.savePreferences(

            context,

            preferences

        )

    }

    suspend fun loadPreferences(): UserPreferences {

        return PreferencesDataStore.loadPreferences(
            context
        )

    }

}