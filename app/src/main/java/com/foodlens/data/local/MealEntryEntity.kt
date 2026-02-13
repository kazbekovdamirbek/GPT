package com.foodlens.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_entries")
data class MealEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbs: Int,
    val createdAt: Long
)
