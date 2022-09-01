package ru.netology.nerecipe.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nerecipe.R
import ru.netology.nerecipe.databinding.RecipeBinding
import ru.netology.nerecipe.dto.Category
import ru.netology.nerecipe.dto.Recipe

class RecipesAdapter(
    private val interactionListener: RecipeInteractionListener
) : ListAdapter<Recipe, RecipesAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    class ViewHolder(
        private val binding: RecipeBinding,
        listener: RecipeInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var recipe: Recipe

        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.options).apply {
                inflate(R.menu.options)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.remove -> {
                            listener.onRemoveButtonClicked(recipe)
                            true
                        }
                        R.id.edit -> {
                            listener.onEditButtonClicked(recipe)
                            true
                        }
                        else -> false
                    }
                }
            }
        }

        init {
            binding.options.setOnClickListener { popupMenu.show() }
        }

        init {
            binding.authorName.setOnClickListener { listener.onRecipeCardClicked(recipe) }
            binding.name.setOnClickListener { listener.onRecipeCardClicked(recipe) }
            binding.avatar.setOnClickListener { listener.onRecipeCardClicked(recipe) }
        }

        init {
            itemView.setOnClickListener { listener.onRecipeItemClicked(recipe) }
            binding.favourite.setOnClickListener { listener.onFavouritesButtonClicked(recipe) }
        }

        fun bind(recipe: Recipe) {
            this.recipe = recipe // кот из lateinit

            with(binding) {
                name.text = recipe.name
                authorName.text = recipe.author
                category.text = category.context.showCategories(recipe.category)
                favourite.isChecked = recipe.addedToFavourites
            }
        }
    }


    private object DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
            oldItem == newItem
    }
}

fun Context.showCategories(category: Category): String {
    return when (category) {
        Category.European -> getString(R.string.european_type)
        Category.Asian -> getString(R.string.asian_type)
        Category.PanAsian -> getString(R.string.panasian_type)
        Category.Eastern -> getString(R.string.eastern_type)
        Category.American -> getString(R.string.american_type)
        Category.Russian -> getString(R.string.russian_type)
        Category.Mediterranean -> getString(R.string.mediterranean_type)
    }
}


