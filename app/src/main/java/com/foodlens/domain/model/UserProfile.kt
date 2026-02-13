package com.foodlens.domain.model

data class UserProfile(
    val goal: UserGoal,
    val sex: String,
    val age: Int,
    val heightCm: Int,
    val weightKg: Int,
    val activityMultiplier: Double
)
