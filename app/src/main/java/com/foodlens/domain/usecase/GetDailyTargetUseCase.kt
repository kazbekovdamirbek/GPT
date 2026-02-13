package com.foodlens.domain.usecase

import com.foodlens.domain.model.DailyNutritionTarget
import com.foodlens.domain.model.UserGoal
import com.foodlens.domain.model.UserProfile
import kotlin.math.roundToInt
import javax.inject.Inject

class GetDailyTargetUseCase @Inject constructor() {
    operator fun invoke(profile: UserProfile): DailyNutritionTarget {
        val bmr = 10 * profile.weightKg + 6.25 * profile.heightCm - 5 * profile.age +
            if (profile.sex.equals("male", ignoreCase = true)) 5 else -161

        val maintenance = bmr * profile.activityMultiplier
        val calories = when (profile.goal) {
            UserGoal.LOSE_WEIGHT -> maintenance - 350
            UserGoal.MAINTAIN -> maintenance
            UserGoal.GAIN_WEIGHT -> maintenance + 300
        }.coerceAtLeast(1200.0)

        val protein = (profile.weightKg * 1.8).roundToInt()
        val fat = (calories * 0.28 / 9).roundToInt()
        val carbs = ((calories - (protein * 4 + fat * 9)) / 4).roundToInt().coerceAtLeast(0)

        return DailyNutritionTarget(
            calories = calories.roundToInt(),
            protein = protein,
            fat = fat,
            carbs = carbs
        )
    }
}
