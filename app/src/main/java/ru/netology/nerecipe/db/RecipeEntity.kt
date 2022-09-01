package ru.netology.nerecipe.db


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nerecipe.dto.Category

@Entity(tableName = "recipes")
class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") // чтобы не слетели имена при релизной сборке
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "category")
    val category: Category,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "addedToFavourites")
    val addedToFavourites: Boolean,
    @ColumnInfo(name = "foodImage")
    val foodImage: String = ""
)


