package com.example.mobileapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RecipeViewModel : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> get() = _recipes

    private val allRecipes = listOf(
        Recipe(1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.blackkarag),
        Recipe(2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.seafood),
        Recipe(3, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake..", R.drawable.takayaki),
        Recipe(4, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable..", R.drawable.temp),
        Recipe(5, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it..", R.drawable.shrimp),
        Recipe(6, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare..", R.drawable.seaf)
    )

    init {
        _recipes.value = allRecipes
    }

    fun searchRecipes(query: String) {
        if (query.length < 3) {
            _recipes.value = allRecipes
        } else {
            val filteredList = allRecipes.filter {
                it.name.contains(query, ignoreCase = true) ||
                        it.description.contains(query, ignoreCase = true)
            }
            if (filteredList != _recipes.value) {
                _recipes.update { filteredList }
            }
        }
    }
}
