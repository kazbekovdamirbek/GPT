package com.foodlens.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.foodlens.domain.model.UserGoal
import com.foodlens.domain.model.UserProfile
import org.junit.Test

class GetDailyTargetUseCaseTest {

    private val useCase = GetDailyTargetUseCase()

    @Test
    fun `returns valid macros for maintain goal`() {
        val profile = UserProfile(
            goal = UserGoal.MAINTAIN,
            sex = "male",
            age = 30,
            heightCm = 180,
            weightKg = 80,
            activityMultiplier = 1.55
        )

        val result = useCase(profile)

        assertThat(result.calories).isGreaterThan(1200)
        assertThat(result.protein).isEqualTo(144)
        assertThat(result.fat).isGreaterThan(0)
        assertThat(result.carbs).isGreaterThan(0)
    }
}
