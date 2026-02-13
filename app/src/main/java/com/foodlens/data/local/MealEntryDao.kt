package com.foodlens.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealEntryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: MealEntryEntity)

    @Query("SELECT * FROM meal_entries ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<MealEntryEntity>>
}
