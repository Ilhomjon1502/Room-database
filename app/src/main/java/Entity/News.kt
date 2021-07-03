package Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 class News{
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
    @ColumnInfo(name = "news_title") // ustun nomi xolos
    var title:String? = null
    @ColumnInfo(name = "description")
    var desc:String?=null

   @ColumnInfo(name = "category_id")
   var categoryId:Int? = null
}