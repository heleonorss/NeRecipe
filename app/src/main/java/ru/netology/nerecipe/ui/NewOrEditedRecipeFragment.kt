package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.netology.nerecipe.R
import ru.netology.nerecipe.adapter.RecipesAdapter
import ru.netology.nerecipe.adapter.showCategories
import ru.netology.nerecipe.data.RecipeRepository
import ru.netology.nerecipe.databinding.NewRecipeFragmentBinding
import ru.netology.nerecipe.databinding.RecipeBinding
import ru.netology.nerecipe.dto.Category
import ru.netology.nerecipe.dto.Recipe
import ru.netology.nerecipe.viewModel.RecipeViewModel

class NewOrEditedRecipeFragment : Fragment() {

    private val args by navArgs<NewOrEditedRecipeFragmentArgs>()

    private val newOrEditedRecipeViewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = NewRecipeFragmentBinding.inflate(layoutInflater, container, false).also { binding ->

        val thisRecipe = args.currentRecipe
        if (thisRecipe != null) {
            with(binding) {
                name.setText(thisRecipe.name)
                content.setText(thisRecipe.content)
                categoryRecipeCheckBox.check(R.id.checkBoxEuropean) // по умолчанию при редактировании ставится
                checkBoxEuropean.text = checkBoxEuropean.context.showCategories(Category.European)
                checkBoxAsian.text = checkBoxAsian.context.showCategories(Category.Asian)
                checkBoxPanasian.text = checkBoxPanasian.context.showCategories(Category.PanAsian)
                checkBoxEastern.text = checkBoxEastern.context.showCategories(Category.Eastern)
                checkBoxAmerican.text = checkBoxAmerican.context.showCategories(Category.American)
                checkBoxRussian.text = checkBoxRussian.context.showCategories(Category.Russian)
                checkBoxMediterranean.text =
                    checkBoxMediterranean.context.showCategories(Category.Mediterranean)
            }
        }

        binding.name.requestFocus()

        binding.categoryRecipeCheckBox.setOnCheckedChangeListener { _, _ ->
            getCheckedCategory(binding.categoryRecipeCheckBox.checkedRadioButtonId)
        }
        binding.ok.setOnClickListener {
            onOkButtonClicked(binding)
        }

    }.root

    private fun onOkButtonClicked(binding: NewRecipeFragmentBinding) {
        val currentRecipe = Recipe(
            id = args.currentRecipe?.id ?: RecipeRepository.NEW_RECIPE_ID,
            name = binding.name.text.toString(),
            content = binding.content.text.toString(),
            category = getCheckedCategory(binding.categoryRecipeCheckBox.checkedRadioButtonId)
        )
        if (emptyFieldsCheck(recipe = currentRecipe)) {
            val resultBundle = Bundle(1)
            resultBundle.putParcelable(RESULT_KEY, currentRecipe)
            setFragmentResult(REQUEST_KEY, resultBundle)
            findNavController().popBackStack() // возвращает на прошлый фрагмент
        }

    }

    // преобразуем отмеченную галочку в категорию
    private fun getCheckedCategory(checkedId: Int) = when (checkedId) {
        R.id.checkBoxEuropean -> Category.European
        R.id.checkBoxAsian -> Category.Asian
        R.id.checkBoxPanasian -> Category.PanAsian
        R.id.checkBoxEastern -> Category.Eastern
        R.id.checkBoxAmerican -> Category.American
        R.id.checkBoxRussian -> Category.Russian
        R.id.checkBoxMediterranean -> Category.Mediterranean
        else -> throw IllegalArgumentException("Unknown type: $checkedId")
    }

    private fun emptyFieldsCheck(recipe: Recipe): Boolean {
        return if (recipe.name.isBlank() && recipe.content.isBlank()) {
            Toast.makeText(activity, "Заполните все поля", Toast.LENGTH_LONG).show()
            false
        } else true
    }

    // чтобы передавать данные между фрагментами
    companion object {
        const val REQUEST_KEY = "requestKey"
        const val RESULT_KEY = "newContent"
    }
}

