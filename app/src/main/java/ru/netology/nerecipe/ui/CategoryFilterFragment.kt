package ru.netology.nerecipe.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.nerecipe.adapter.RecipesAdapter
import ru.netology.nerecipe.adapter.showCategories
import ru.netology.nerecipe.databinding.CategoryFiltersBinding
import ru.netology.nerecipe.databinding.NewRecipeFragmentBinding
import ru.netology.nerecipe.dto.Category
import ru.netology.nerecipe.viewModel.RecipeViewModel

class CategoryFilterFragment : Fragment() {

    private val categoryFilterViewModel: RecipeViewModel by viewModels(ownerProducer = ::requireParentFragment)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = CategoryFiltersBinding.inflate(layoutInflater, container, false).also { binding ->

        with(binding) {
            checkBoxEuropean.text = checkBoxEuropean.context.showCategories(Category.European)
            checkBoxAsian.text = checkBoxAsian.context.showCategories(Category.Asian)
            checkBoxPanasian.text = checkBoxPanasian.context.showCategories(Category.PanAsian)
            checkBoxEastern.text = checkBoxEastern.context.showCategories(Category.Eastern)
            checkBoxAmerican.text = checkBoxAmerican.context.showCategories(Category.American)
            checkBoxRussian.text = checkBoxRussian.context.showCategories(Category.Russian)
            checkBoxMediterranean.text =
                checkBoxMediterranean.context.showCategories(Category.Mediterranean)

            binding.ok.setOnClickListener {
                onOkButtonClicked(binding)
            }
        }

    }.root

    private fun onOkButtonClicked(binding: CategoryFiltersBinding) {

        val categoryList = arrayListOf<Category>()
        var checkedCount = 7
        val nothingIsChecked = 0

        if (binding.checkBoxEuropean.isChecked) {
            categoryList.add(Category.European)
            categoryFilterViewModel.setCategoryFilter = true
        } else {
            --checkedCount
        }

        if (binding.checkBoxAsian.isChecked) {
            categoryList.add(Category.Asian)
            categoryFilterViewModel.setCategoryFilter = true
        } else {
            --checkedCount
        }

        if (binding.checkBoxPanasian.isChecked) {
            categoryList.add(Category.PanAsian)
            categoryFilterViewModel.setCategoryFilter = true
        } else {
            --checkedCount
        }

        if (binding.checkBoxEastern.isChecked) {
            categoryList.add(Category.Eastern)
            categoryFilterViewModel.setCategoryFilter = true
        } else {
            --checkedCount
        }

        if (binding.checkBoxAmerican.isChecked) {
            categoryList.add(Category.American)
            categoryFilterViewModel.setCategoryFilter = true
        } else {
            --checkedCount
        }

        if (binding.checkBoxRussian.isChecked) {
            categoryList.add(Category.Russian)
            categoryFilterViewModel.setCategoryFilter = true
        } else {
            --checkedCount
        }

        if (binding.checkBoxMediterranean.isChecked) {
            categoryList.add(Category.Mediterranean)
            categoryFilterViewModel.setCategoryFilter = true
        } else {
            --checkedCount
        }

        if (checkedCount == nothingIsChecked) {
            Toast.makeText(activity, "Ай-яй! Нельзя убирать все фильтры", Toast.LENGTH_LONG).show()
        } else {
            categoryFilterViewModel.showRecipesByCategories(categoryList)
            val resultBundle = Bundle(1)
            resultBundle.putParcelableArrayList(CHECKBOX_KEY, categoryList)
            setFragmentResult(CHECKBOX_KEY, resultBundle)
            findNavController().popBackStack()
        }
    }

    // чтобы передавать данные между фрагментами
    companion object {
        const val CHECKBOX_KEY = "checkBoxContent"
    }
}