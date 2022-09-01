package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.adapter.RecipesAdapter
import ru.netology.nerecipe.databinding.FavouriteFragmentBinding
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.viewModel.RecipeViewModel

class FavouriteRecipeFragment : Fragment() {

    private val favouriteRecipeViewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FavouriteFragmentBinding.inflate(layoutInflater, container, false).also { binding ->
        val adapter = RecipesAdapter(favouriteRecipeViewModel)
        binding.recipesRecycler.adapter = adapter

        favouriteRecipeViewModel.data.observe(viewLifecycleOwner) { recipes ->

            val favouriteRecipes = recipes.filter { it.addedToFavourites }
            adapter.submitList(favouriteRecipes)

            val emptyList = recipes.none { it.addedToFavourites }
            binding.textEmptyList.visibility =
                if (emptyList) View.VISIBLE else View.GONE
            binding.iconEmptyList.visibility =
                if (emptyList) View.VISIBLE else View.GONE
        }

        //организация перехода к фрагменту separateRecipeFragment
        favouriteRecipeViewModel.separateRecipeViewEvent.observe(viewLifecycleOwner) { recipeCardId ->
            val direction =
                FavouriteRecipeFragmentDirections.actionFavouriteRecipeFragmentToSeparateRecipeFragment(
                    recipeCardId
                )
            findNavController().navigate(direction)
        }

        //организация перехода к фрагменту NewOrEditedRecipeFragment
        favouriteRecipeViewModel.navigateToRecipeContentScreenEvent.observe(viewLifecycleOwner) { recipe ->
            val direction =
                FavouriteRecipeFragmentDirections.actionFavouriteRecipeFragmentToNewOrEditedRecipeFragment(
                    recipe
                )
            findNavController().navigate(direction)
        }
    }.root

    override fun onResume() {
        super.onResume()
        // показываем новый экран в нашем приложении
        // данная ф-ция будет вызвана при завершении NewOrEditedRecipeFragment
        setFragmentResultListener(
            requestKey = NewOrEditedRecipeFragment.REQUEST_KEY
        ) { requestKey, bundle ->
            if (requestKey != NewOrEditedRecipeFragment.REQUEST_KEY) return@setFragmentResultListener
            val newRecipe = bundle.getParcelable<Recipe>(
                NewOrEditedRecipeFragment.RESULT_KEY
            ) ?: return@setFragmentResultListener
            favouriteRecipeViewModel.onSaveButtonClicked(newRecipe)
        }
    }

}


