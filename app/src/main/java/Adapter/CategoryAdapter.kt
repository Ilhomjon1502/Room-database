package Adapter

import Entity.Category
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ilhomjon.roomdatabase.databinding.ItemCategoryBinding

class CategoryAdapter(var list: List<Category>):BaseAdapter() {

    override fun getCount(): Int = list.size
    override fun getItem(position: Int): Category = list[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val categoryViewHolder:CategoryViewHolder
        if (convertView == null){
            categoryViewHolder = CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent?.context)))
        }else{
            categoryViewHolder = CategoryViewHolder(ItemCategoryBinding.bind(convertView))
        }

        categoryViewHolder.itemCategoryBinding.txtItemSpinner.text = list[position].name

        return categoryViewHolder.itemView
    }

    inner class CategoryViewHolder(var itemCategoryBinding: ItemCategoryBinding){
        var itemView: View
        init {
            itemView = itemCategoryBinding.root
        }
    }
}