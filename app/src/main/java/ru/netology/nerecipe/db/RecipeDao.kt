package ru.netology.nerecipe.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.dto.Category

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes ORDER BY id DESC")
    fun getAll(): LiveData<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(recipe: RecipeEntity)

    @Query("UPDATE recipes SET name = :name, content = :content, category = :category WHERE id = :id")
    fun updateById(
        id: Long, name: String,
        content: String, category: Category
    )

    fun save(recipe: RecipeEntity) =
        if (recipe.id == RecipeRepository.NEW_RECIPE_ID)
            insert(recipe)
        else updateById(recipe.id, recipe.name, recipe.content, recipe.category)

    @Query("DELETE FROM recipes WHERE id = :id")
    fun delete(id: Long)

    @Query(
        """
        UPDATE recipes SET
        addedToFavourites = CASE WHEN addedToFavourites THEN 0 ELSE 1 END
        WHERE id = :id
        """
    )
    fun addToFavourites(id: Long)

    @Query("SELECT * FROM recipes WHERE name LIKE '%' || :text || '%'")
    fun search(text: String): LiveData<List<RecipeEntity>>

    @Query("SELECT * FROM recipes WHERE category = :categoryRecipe")
    fun getCategory(categoryRecipe: Category): LiveData<List<RecipeEntity>>

    @Query("UPDATE recipes SET name = name")
    fun update()


}