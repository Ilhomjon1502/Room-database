package DAO

import Entity.CategoryNews
import Entity.News
import androidx.room.*

@Dao
interface NewsDao {

    @Query("select * from news")
    fun getAllNews():List<News>

    @Insert
    fun addNews(news: News)

    @Delete
    fun deleteNew(news: News)

    @Update
    fun updateNew(news: News)

    @Query("select * from news where id=:id")
    fun getNewsById(id:Int):News

    @Query("select * from news where news_title=:title and description=:desc")
    fun getNewsById(title:String, desc:String):Int

    @Insert
    fun addAllNew(vararg news: News)


}