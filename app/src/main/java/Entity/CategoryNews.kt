package Entity

import androidx.room.Embedded
import androidx.room.Relation


data class CategoryNews(
        @Embedded
        val category: Category, // aaosiy table
        @Relation(
                parentColumn = "id",
                entityColumn = "category_id"
        )
        val newsList: List<News>
)
//News va category classlarini bog'lash uchun