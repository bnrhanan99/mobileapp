package com.example.mobileapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val context: Context,
    private var recipes: List<Recipe>
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "Clicked on ${recipe.name}", Toast.LENGTH_SHORT).show()
        }

        holder.share.setOnClickListener {
            Toast.makeText(context, "Share ${recipe.name}", Toast.LENGTH_SHORT).show()
        }

        holder.like.setOnClickListener {
            Toast.makeText(context, "Liked ${recipe.name}", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateRecipes(newRecipes: List<Recipe>) {
        if (newRecipes != recipes) {
            recipes = newRecipes
            notifyDataSetChanged()
        }
    }

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.title)
        private val description: TextView = view.findViewById(R.id.description)
        private val image: ImageView = view.findViewById(R.id.image)
        val share: ImageButton = view.findViewById(R.id.shareButton)
        val like: ImageButton = view.findViewById(R.id.likebutton)

        fun bind(recipe: Recipe) {
            title.text = recipe.name
            description.text = recipe.description
            image.setImageResource(recipe.imageResId)
        }
    }
}
