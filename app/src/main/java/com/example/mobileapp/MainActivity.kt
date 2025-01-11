package com.example.mobileapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recipesRecyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recipeRecyclerView)
        val recipeAdapter = RecipeAdapter(this, emptyList())

        recipesRecyclerView.layoutManager = LinearLayoutManager(this)
        recipesRecyclerView.adapter = recipeAdapter

        val searchView = findViewById<androidx.appcompat.widget.SearchView>(R.id.searchView)

        lifecycleScope.launch {
            viewModel.recipes.collect { recipes ->
                recipeAdapter.updateRecipes(recipes)
            }
        }

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchRecipes(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchRecipes(it) }
                return true
            }
        })
    }
}
