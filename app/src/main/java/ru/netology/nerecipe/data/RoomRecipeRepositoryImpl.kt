package ru.netology.nerecipe.data


import androidx.lifecycle.map
import ru.netology.nerecipe.db.RecipeDao
import ru.netology.nerecipe.db.toEntity
import ru.netology.nerecipe.db.toModel
import ru.netology.nerecipe.dto.Category
import ru.netology.nerecipe.dto.Recipe

class RoomRecipeRepositoryImpl(
    private val dao: RecipeDao
) : RecipeRepository {

    override var data = dao.getAll().map { entities ->
        entities.map { it.toModel() }
    }

    override fun save(recipe: Recipe) {
        dao.save(recipe.toEntity())
    }

    override fun delete(recipeId: Long) {
        dao.delete(recipeId)
    }

    override fun addToFavourites(recipeId: Long) {
       dao.addToFavourites(recipeId)
    }

    override fun search(recipeName: String) {
        dao.search(recipeName)
    }

    override fun getCategory(category: Category) {
        dao.getCategory(category)
    }

    override fun update() {
        dao.update()
    }
}