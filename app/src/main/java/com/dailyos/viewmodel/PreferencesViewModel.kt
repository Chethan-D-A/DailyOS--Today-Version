package com.dailyos.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dailyos.model.ThemeMode
import com.dailyos.model.UserPreferences
import com.dailyos.repository.PreferencesRepository
import kotlinx.coroutines.launch
import com.dailyos.repository.DailyRepository

class PreferencesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository =
        PreferencesRepository(
            getApplication()
        )

    var preferences by mutableStateOf(
        UserPreferences()
    )
        private set

    init {

        loadPreferences()

    }

    private fun loadPreferences() {

        viewModelScope.launch {

            preferences =
                repository.loadPreferences()

        }

    }

    private fun savePreferences() {

        viewModelScope.launch {

            repository.savePreferences(
                preferences
            )

        }

    }

    fun updateSleepGoal(
        value: Double
    ) {

        preferences =
            preferences.copy(
                sleepGoal = value
            )

        savePreferences()

    }

    fun updateWaterGoal(
        value: Double
    ) {

        preferences =
            preferences.copy(
                waterGoal = value
            )

        savePreferences()

    }

    fun updateExerciseGoal(
        value: Double
    ) {

        preferences =
            preferences.copy(
                exerciseGoal = value
            )

        savePreferences()

    }

    fun updateOutsideGoal(
        value: Double
    ) {

        preferences =
            preferences.copy(
                outsideGoal = value
            )

        savePreferences()

    }

    fun updateFamilyGoal(
        value: Double
    ) {

        preferences =
            preferences.copy(
                familyGoal = value
            )

        savePreferences()

    }

    fun updateTheme(
        theme: ThemeMode
    ) {

        preferences =
            preferences.copy(
                theme = theme
            )

        savePreferences()

    }

    fun updateTodaySleep(
        value: Double
    ) {

        viewModelScope.launch {

            val repository =
                DailyRepository(
                    getApplication()
                )

            repository.saveSleep(
                value
            )

        }

    }

    fun loadTodaySleep(
        onLoaded: (Double) -> Unit
    ) {

        viewModelScope.launch {

            val repository =
                DailyRepository(
                    getApplication()
                )

            onLoaded(

                repository.loadSleep()

            )

        }

    }

}