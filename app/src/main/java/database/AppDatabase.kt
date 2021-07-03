package database

import DAO.CategoryDao
import DAO.NewsDao
import Entity.Category
import Entity.News
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun newDao():NewsDao
    abstract fun categoryDao():CategoryDao

    companion object{
        private var instance:AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context):AppDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "news_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}