package Adapter

import Entity.News
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhomjon.roomdatabase.databinding.ItmRvBinding

class NewsAdapter(val list:List<News>, val rvItemClick: RvItemClick)
    :RecyclerView.Adapter<NewsAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItmRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){

        fun onBind(news: News, position: Int){
            itemRvBinding.txt1.text = news.title
            itemRvBinding.txt2.text = news.desc

            itemRvBinding.btnDelete.setOnClickListener {
                rvItemClick.itemDelete(news, position)
            }
            itemRvBinding.btnEdit.setOnClickListener {
                rvItemClick.itemEdit(news, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItmRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}
interface RvItemClick{
    fun itemDelete(news: News, position: Int)
    fun itemEdit(news: News, position: Int)
}