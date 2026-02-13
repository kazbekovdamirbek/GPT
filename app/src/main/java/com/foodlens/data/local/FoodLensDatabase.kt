package com.foodlens.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MealEntryEntity::class], version = 1, exportSchema = false)
abstract class FoodLensDatabase : RoomDatabase() {
    abstract fun mealEntryDao(): MealEntryDao
}
