package com.dailyos.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.dailyos.data.DefaultData
import com.dailyos.engine.RequirementEngine
import com.dailyos.model.ActivityLog
import com.dailyos.model.DailyActivity
import com.dailyos.model.DayState
import com.dailyos.model.Goal
import com.dailyos.model.Requirement
import com.dailyos.util.DayManager
import java.time.LocalTime
import com.dailyos.repository.DailyRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.dailyos.repository.PreferencesRepository
import com.dailyos.model.UserPreferences

class TodayViewModel(
    application: Application
) : AndroidViewModel(application)
{

    private fun saveGoals() {

        viewModelScope.launch {

            repository.saveGoals(
                goals.toList()
            )

        }

    }

    private fun saveActivityLogs() {

        viewModelScope.launch {

            repository.saveActivityLogs(
                activityLogs.toList()
            )

        }

    }

    private val repository = DailyRepository(
        getApplication()
    )

    private val preferencesRepository =
        PreferencesRepository(
            getApplication()
        )

    private var preferences =
        UserPreferences()

    var dayState by mutableStateOf(
        DayManager.createToday()
    )
        private set

    val goals = mutableStateListOf<Goal>().apply {
        addAll(DefaultData.goals())
    }

    val requirements = mutableStateListOf<Requirement>()

    val activities = DefaultData.activities()

    val activityLogs = mutableStateListOf<ActivityLog>()

    init {

        loadPreferences()

        loadGoals()

        loadActivityLogs()

        loadSleep()

    }

    private fun loadPreferences() {

        viewModelScope.launch {

            preferences =
                preferencesRepository
                    .loadPreferences()

            rebuildRequirements()

        }

    }

    private fun createRequirements(): List<Requirement> {

        return listOf(

            Requirement(
                "Sleep",
                "h",
                preferences.sleepGoal,
                0.0
            ),

            Requirement(
                "Meals",
                "",
                3.0,
                0.0
            ),

            Requirement(
                "Water",
                "L",
                preferences.waterGoal,
                0.0
            ),

            Requirement(
                "Exercise",
                "min",
                preferences.exerciseGoal,
                0.0
            ),

            Requirement(
                "Outside",
                "min",
                preferences.outsideGoal,
                0.0
            ),

            Requirement(
                "Family",
                "min",
                preferences.familyGoal,
                0.0
            )

        )

    }

    private fun loadGoals() {

        viewModelScope.launch {

            val savedGoals = repository.loadGoals()

            if (savedGoals.isNotEmpty()) {

                goals.clear()

                goals.addAll(savedGoals)

            }

        }

    }

    private fun loadActivityLogs() {

        viewModelScope.launch {

            val savedLogs =
                repository.loadActivityLogs()

            if (savedLogs.isNotEmpty()) {

                activityLogs.clear()

                activityLogs.addAll(savedLogs)

            }

            rebuildRequirements()

        }

    }

    private fun loadSleep() {

        viewModelScope.launch {

            val hours = repository.loadSleep()


            dayState = dayState.copy(

                sleepHours = hours

            )

            val sleepRequirement = requirements.firstOrNull {

                it.name == "Sleep"

            }

            if (sleepRequirement != null) {

                val index = requirements.indexOf(sleepRequirement)

                requirements[index] = sleepRequirement.copy(
                    current = hours
                )

            }


            rebuildRequirements()

        }


    }

    private fun rebuildRequirements() {

        requirements.clear()

        requirements.addAll(
            createRequirements()
        )

        // Restore sleep

        val sleepRequirement = requirements.firstOrNull {

            it.name == "Sleep"

        }

        if (sleepRequirement != null) {

            val index = requirements.indexOf(sleepRequirement)

            requirements[index] = sleepRequirement.copy(
                current = dayState.sleepHours
            )

        }

        // Replay every activity

        activityLogs.forEach { log ->

            val activity = activities.firstOrNull {

                it.title == log.title

            } ?: return@forEach

            val duration = if (log.duration.isBlank()) {

                0

            } else {

                log.duration
                    .substringBefore(" ")
                    .toInt()

            }

            RequirementEngine.logActivity(

                activity = activity,

                duration = duration,

                requirements = requirements

            )

        }

    }

    fun toggleGoal(goal: Goal) {

        val index = goals.indexOf(goal)

        if (index != -1) {

            goals[index] = goal.copy(
                completed = !goal.completed
            )

            saveGoals()

        }

    }

    fun deleteGoal(goal: Goal) {

        goals.remove(goal)

        saveGoals()

    }

    fun addGoal(title: String) {

        val trimmed = title.trim()

        if (trimmed.isBlank()) return

        goals.add(
            Goal(trimmed)
        )

        saveGoals()
    }

    fun logActivity(
        activity: DailyActivity,
        duration: Int
    ) {

        RequirementEngine.logActivity(
            activity,
            duration,
            requirements
        )

        activityLogs.add(

            ActivityLog(

                time = LocalTime.now()
                    .withSecond(0)
                    .withNano(0)
                    .toString(),

                icon = activity.icon,

                title = activity.title,

                duration =
                    if (duration == 0)
                        ""
                    else
                        "$duration min"

            )



        )

        saveActivityLogs()

    }

    fun deleteActivity(
        log: ActivityLog
    ) {

        val activity = activities.firstOrNull {

            it.title == log.title

        } ?: return

        val duration =
            if (log.duration.isBlank())
                0
            else
                log.duration
                    .substringBefore(" ")
                    .toInt()

        RequirementEngine.removeActivity(
            activity,
            duration,
            requirements
        )

        activityLogs.remove(log)

        saveActivityLogs()

    }

    fun completionPercentage(): Int {

        return RequirementEngine
            .completionPercentage(requirements)

    }

    fun initializeDay() {

        viewModelScope.launch {

            val today = DayManager.todayString()

            val lastOpen =
                repository.loadLastOpenDate()

            if (lastOpen == null) {

                repository.saveLastOpenDate(today)

                repository.saveSleep(0.0)

                return@launch

            }

            if (lastOpen != today) {

                // ---------- Reset memory ----------

                goals.clear()
                goals.addAll(DefaultData.goals())

                activityLogs.clear()

                requirements.clear()
                requirements.addAll(
                    createRequirements()
                )

                dayState = DayManager.createToday()


                // ---------- Reset persistence ----------

                repository.saveGoals(
                    goals.toList()
                )

                repository.saveActivityLogs(
                    emptyList()
                )

                repository.saveSleep(
                    0.0
                )


                repository.saveLastOpenDate(
                    today
                )

            }

        }

    }

    fun forceNewDay() {

        viewModelScope.launch {

            val today = DayManager.todayString()

            // ---------- Reset memory ----------

            goals.clear()
            goals.addAll(
                DefaultData.goals()
            )

            activityLogs.clear()

            requirements.clear()
            requirements.addAll(
                createRequirements()
            )

            dayState = DayManager.createToday()


            // ---------- Reset persistence ----------

            repository.saveGoals(
                goals.toList()
            )

            repository.saveActivityLogs(
                emptyList()
            )

            repository.saveSleep(
                0.0
            )


            repository.saveLastOpenDate(
                today
            )

        }

    }
}

