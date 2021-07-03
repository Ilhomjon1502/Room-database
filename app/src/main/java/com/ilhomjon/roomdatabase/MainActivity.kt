package com.ilhomjon.roomdatabase

import Adapter.CategoryAdapter
import Adapter.NewsAdapter
import Adapter.RvItemClick
import Entity.Category
import Entity.News
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.ilhomjon.roomdatabase.databinding.ActivityMainBinding
import com.ilhomjon.roomdatabase.databinding.ItemDialogBinding
import database.AppDatabase

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var appDatabase: AppDatabase
    lateinit var list: ArrayList<News>
    lateinit var newsAdapter: NewsAdapter
    lateinit var categoryList:ArrayList<Category>
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appDatabase = AppDatabase.getInstance(this)

//        val category1 = Category("Dunyo")
//        val category2 = Category("Texnologiya")
//        val category3 = Category("Mahalliy")
//
//        appDatabase.categoryDao().addAllCategory(category1, category2, category3)

        categoryList = ArrayList()
        categoryList.addAll(appDatabase.categoryDao().getAllCategories())

        categoryAdapter = CategoryAdapter(categoryList)
        binding.spinner.adapter = categoryAdapter


        list = ArrayList()

        list.addAll(appDatabase.newDao().getAllNews())

        newsAdapter = NewsAdapter(list, object : RvItemClick{
            override fun itemDelete(news: News, position: Int) {

                appDatabase.newDao().deleteNew(news)
                list.remove(news)
                newsAdapter.notifyItemRemoved(position)
                newsAdapter.notifyItemRangeChanged(position, list.size)
            }

            override fun itemEdit(news: News, position: Int) {
                val dialog = AlertDialog.Builder(this@MainActivity)
                val dialogBinding = ItemDialogBinding.inflate(layoutInflater)
                dialog.setView(dialogBinding.root)
                dialogBinding.edt1.setText(news.title)
                dialogBinding.edt2.setText(news.desc)

                dialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val title1 = dialogBinding.edt1.text.toString()
                        val desc1 = dialogBinding.edt2.text.toString()

                        news.title = title1
                        news.desc = desc1
                        appDatabase.newDao().updateNew(news)
                        newsAdapter.notifyItemChanged(position)
                    }
                })
                dialog.show()
            }
        })

        binding.rv.adapter = newsAdapter

        binding.addBtn.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val desc = binding.edtDesc.text.toString()

            val news = News()
            news.title = title
            news.desc = desc

            val selectedItemPosition = binding.spinner.selectedItemPosition
            val category = categoryList[selectedItemPosition]
            news.categoryId = category.id

            appDatabase.newDao().addNews(news)

            val id = appDatabase.newDao().getNewsById(title, desc)
            news.id = id

            list.add(news)
            newsAdapter.notifyItemInserted(list.size)
        }
    }
}