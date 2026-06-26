package com.dailyos.engine

import com.dailyos.model.DailyActivity
import com.dailyos.model.Requirement

object RequirementEngine {

    fun logActivity(
        activity: DailyActivity,
        duration: Int,
        requirements: MutableList<Requirement>
    ) {
        update(
            activity = activity,
            duration = duration,
            multiplier = 1,
            requirements = requirements
        )
    }

    fun removeActivity(
        activity: DailyActivity,
        duration: Int,
        requirements: MutableList<Requirement>
    ) {
        update(
            activity = activity,
            duration = duration,
            multiplier = -1,
            requirements = requirements
        )
    }

    private fun update(
        activity: DailyActivity,
        duration: Int,
        multiplier: Int,
        requirements: MutableList<Requirement>
    ) {

        for (i in requirements.indices) {

            val requirement = requirements[i]

            val updated = when (activity.id) {

                "walk" -> {

                    when (requirement.name) {

                        "Exercise" -> requirement.copy(
                            current = (
                                    requirement.current +
                                            duration * multiplier
                                    ).coerceIn(0.0, requirement.target)
                        )

                        "Outside" -> requirement.copy(
                            current = (
                                    requirement.current +
                                            duration * multiplier
                                    ).coerceIn(0.0, requirement.target)
                        )

                        else -> requirement

                    }

                }

                "workout" -> {

                    if (requirement.name == "Exercise") {

                        requirement.copy(
                            current = (
                                    requirement.current +
                                            duration * multiplier
                                    ).coerceIn(0.0, requirement.target)
                        )

                    } else requirement

                }

                "family" -> {

                    if (requirement.name == "Family") {

                        requirement.copy(
                            current = (
                                    requirement.current +
                                            duration * multiplier
                                    ).coerceIn(0.0, requirement.target)
                        )

                    } else requirement

                }

                "meal" -> {

                    if (requirement.name == "Meals") {

                        requirement.copy(
                            current = (
                                    requirement.current +
                                            1.0 * multiplier
                                    ).coerceIn(0.0, requirement.target)
                        )

                    } else requirement

                }

                "water" -> {

                    if (requirement.name == "Water") {

                        requirement.copy(
                            current = (
                                    requirement.current +
                                            0.25 * multiplier
                                    ).coerceIn(0.0, requirement.target)
                        )

                    } else requirement

                }

                else -> requirement

            }

            requirements[i] = updated

        }

    }

    fun completionPercentage(
        requirements: List<Requirement>
    ): Int {

        if (requirements.isEmpty()) return 0

        val progress = requirements.sumOf {

            (it.current / it.target)
                .coerceIn(0.0, 1.0)

        }

        return ((progress / requirements.size) * 100).toInt()

    }

}