package com.artbonix.colorapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ColorDao {
    @Query("SELECT * FROM colors")
    fun getAllColors(): LiveData<List<ColorEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColor(color: ColorEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertColors(colors: List<ColorEntity>)

    @Query("DELETE FROM colors WHERE id = :colorId")
    suspend fun deleteColor(colorId: Int)

    @Query("DELETE FROM colors")
    suspend fun deleteAllColors()

    @Transaction
    suspend fun updateColors(colors: List<ColorEntity>) {
        deleteAllColors()
        insertColors(colors)
    }
}
