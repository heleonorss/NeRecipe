package ru.netology.nerecipe.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nerecipe.dto.Category
import ru.netology.nerecipe.dto.Recipe

object InMemoryRecipeRepositoryImpl : RecipeRepository {
    private var nextId = 1L

    private var recipes = listOf(
        Recipe(
            id = nextId++,
            name = "Карбонара",
            author = "Автор: Юлия Высоцкая",
            category = Category.European,
            content = "1. Бекон нарезаем соломкой. Чеснок продавливаем через чеснокодавку или мелко нарезаем.\n" +
                    "\n" +
                    "2. На сковороде разогреваем немного растительного масла и на нём слегка обжариваем чеснок. Далее добавляем бекон и хорошо обжариваем.\n" +
                    "\n" +
                    "3. Сыр натираем на мелкой тёрке. В глубокую тарелку или миску выкладываем яичные желтки, солим их, перчим и хорошо взбиваем. Добавляем сливки и тёртый сыр и снова тщательно перемешиваем.\n" +
                    "\n" +
                    "4. Спагетти отвариваем до готовности в соответствии с рекомендациями производителя на упаковке, очень важно не переварить их (чтобы правильно сварить спагетти существует одно правило: на 100 грамм спагетти необходимо 1 литр воды и 10 грамм соли). Сливаем воду.\n" +
                    "\n" +
                    "5. Выкладываем горячие спагетти в глубокую сковороду, добавляем яично-сливочный соус и хорошо перемешиваем.\n" +
                    "\n" +
                    "6. Сверху выкладываем жареный бекон и ещё раз всё тщательно перемешиваем.\n" +
                    "\n" +
                    "7. Паста Карбонара готова, подавать её следует горячей, можно ешё посыпать сверху мелко тёртым пармезаном. Приятного аппетита!",
            addedToFavourites = false,
            foodImage = "@drawable/food_image"
        ),
        Recipe(
            id = nextId++,
            name = "Том Ям",
            author = "Автор: Kulinarista",
            category = Category.Asian,
            content = "1. Готовим бульон без соли (или используем 1 бульонный кубик на 0,5 литра воды).\n" +
                    "\n" +
                    "2. Добавляем приправы: стебли лемонграсса (или цедру лайма), нарезанный галангал, листья каффира. Провариваем все 3-5 минут и вылавливаем из бульона.\n" +
                    "\n" +
                    "3. В кипящий бульон добавляем пасту Том Ям, очищенные креветки и нарезанные на крупные кусочки грибы. Провариваем при небольшом кипении около 5 минут.\n" +
                    "\n" +
                    "4. Добавляем рыбный соус, нарезанный колечками чили, сок лайма и порезанный кубиками помидор без шкурки.\n" +
                    "\n" +
                    "5. Вливаем кокосовое молоко. Варим после закипания 2-3 минуты.\n" +
                    "\n" +
                    "6. Осталось посыпать кинзой или зеленым луком — и суп Том Ям готов!",
            addedToFavourites = false
        ),
        Recipe(
            id = nextId++,
            name = "Блины",
            author = "Автор: Семейный Очаг",
            category = Category.Russian,
            content = "Шаг 1.\n" +
                    "\n" +
                    "В глубокую посуду вбиваем яйца, сразу сахар, соль и молоко. Взбиваем все венчиком или миксером.\n" +
                    "\n" +
                    "Шаг 2.\n" +
                    "\n" +
                    "Добавляем растительное масло перемешаем.\n" +
                    "\n" +
                    "Шаг 3.\n" +
                    "\n" +
                    "Теперь добавляем муку ложками с горочкой. Добавляем не все сразу а порциями и постоянно перемешиваем, чтобы не образовались комочки.\n" +
                    "\n" +
                    "Шаг 4.\n" +
                    "\n" +
                    "Оставляем на столе, а тем временем разогреваем плиту и ставим сковородку нагреваться. Жарить будем на сливочном масле, так блинчики вкуснее получаются, приобретают нежный сливочный вкус. Растапливаем маленький кусочек на сковороде.\n" +
                    "\n" +
                    "Шаг 5.\n" +
                    "\n" +
                    "Теперь жарим блинчики как обычно, поварёшкой наливаем в центр сковороды и круговыми движениями распределяем тесто по поверхности. Обжариваем с обеих сторон. После можно подавать на завтрак со сметаной или сгущенным молоком.",
            addedToFavourites = false
        ),
        Recipe(
            id = nextId++,
            name = "Пшенная каша на молоке",
            author = "Автор: Лефуд",
            category = Category.Russian,
            content = "Шаг 1.\n" +
                    "\n" +
                    "Пшено промойте под проточной водой и всыпьте в чашу мультиварки.\n" +
                    "\n" +
                    "Шаг 2.\n" +
                    "\n" +
                    "Добавьте соль, сахар, воду и молоко. Закройте крышку и готовьте полчаса на режиме «Каша».\n" +
                    "\n" +
                    "Шаг 3.\n" +
                    "\n" +
                    "После звукового сигнала включите режим «Подогрев» и настаивайте готовое блюдо еще 10 минут.\n" +
                    "\n" +
                    "Шаг 4.\n" +
                    "\n" +
                    "Разложите кашу по тарелкам и добавьте сливочное масло.",

            addedToFavourites = false
        )
    )

    override val data = MutableLiveData(recipes)

    override fun save(recipe: Recipe) {
        if (recipe.id == RecipeRepository.NEW_RECIPE_ID) insert(recipe) else update(recipe)
    }

    override fun delete(recipeId: Long) {
        recipes =
            recipes.filter { it.id != recipeId }  // фильтруем список рецептов по Ид и оставляем только те, кот не хотим удалить
        data.value = recipes
    }

    override fun addToFavourites(recipeId: Long) {
        recipes =
            recipes.map {
                if (it.id == recipeId)
                    it.copy(addedToFavourites = !it.addedToFavourites)
                else it
            }
        data.value = recipes
    }

    override fun search(recipeName: String) {
        recipes.find {
            it.name == recipeName
        } ?: throw RuntimeException("Ничего не найдено")
        data.value = recipes
    }

    override fun getCategory(category: Category) {
        recipes.find {
            it.category == category
        }
        data.value = recipes
    }

    override fun update() {
        data.value = recipes
    }

    private fun update(recipe: Recipe) {
        recipes = recipes.map {
            if (it.id == recipe.id) recipe else it // Если Ид совпадают, то возвращаем новый рецепт, если нет, то старый рецепт
        }
        data.value = recipes
    }

    private fun insert(recipe: Recipe) {
        recipes =
            listOf( // оборачиваем в список для того, чтобы наш ИД оказался впереди списка рецептов, а не в конце
                recipe.copy(
                    id = nextId++
                )
            ) + recipes
        data.value = recipes
    }
}