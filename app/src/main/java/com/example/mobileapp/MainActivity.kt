package com.example.mobileapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: Int
) 

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipes = listOf(
            Recipe(1, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka...", R.drawable.blackkarag),
            Recipe(2, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare...", R.drawable.seafood),
            Recipe(3, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake..", R.drawable.takayaki),
            Recipe(4, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable..", R.drawable.temp),
            Recipe(5, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it..", R.drawable.shrimp),
            Recipe(6, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare..", R.drawable.seaf)
        )

        val recipesRecyclerView = findViewById<RecyclerView>(R.id.recipeRecyclerView)
        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesRecyclerView.adapter = RecipeAdapter(this, recipes)
    }
}