package Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Category {

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    var name:String? = null

    constructor(name: String?) {
        this.name = name
    }
}