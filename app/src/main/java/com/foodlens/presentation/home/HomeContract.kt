package com.foodlens.presentation.home

import com.foodlens.domain.model.DailyNutritionTarget

data class HomeUiState(
    val target: DailyNutritionTarget? = null,
    val emptyStateMessage: String = "Заполните профиль, чтобы увидеть дневной план"
)
