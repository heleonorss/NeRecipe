package ru.netology.nerecipe.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import ru.netology.nerecipe.R

@Serializable
@Parcelize
data class Recipe(
    val id: Long,
    val name: String,
    val author: String = "",
    val category: Category,
    val content: String,
    val addedToFavourites: Boolean = false,
    val foodImage: String = ""

) : Parcelable

@Serializable
@Parcelize
enum class Category: Parcelable {
    European,
    Asian,
    PanAsian,
    Eastern,
    American,
    Russian,
    Mediterranean
}






