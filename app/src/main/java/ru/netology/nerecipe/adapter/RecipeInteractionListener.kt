package ru.netology.nerecipe.adapter

import ru.netology.nerecipe.dto.Recipe

interface RecipeInteractionListener {

    fun onRemoveButtonClicked(recipe: Recipe)
    fun onEditButtonClicked(recipe: Recipe)
    fun onRecipeCardClicked(recipe: Recipe)
    fun onFavouritesButtonClicked(recipe: Recipe)
    fun onRecipeItemClicked(recipe: Recipe)
}