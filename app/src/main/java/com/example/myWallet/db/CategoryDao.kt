package com.example.myWallet.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myWallet.models.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewCategory(cat: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDefaultCategory(cat: List<Category>)

    @Query("SELECT * FROM CATEGORY")
    suspend fun getAllCategories(): List<Category>
}