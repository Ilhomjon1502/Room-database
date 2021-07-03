package DAO

import Entity.Category
import Entity.CategoryNews
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface CategoryDao {

    @Transaction
    @Query("select * from category")
    fun getCategoryByNews():List<CategoryNews>

    @Query("select * from category")
    fun getAllCategories():List<Category>

    @Insert
    fun addCategory(category: Category)

    @Insert
    fun addAllCategory(vararg category: Category)
}