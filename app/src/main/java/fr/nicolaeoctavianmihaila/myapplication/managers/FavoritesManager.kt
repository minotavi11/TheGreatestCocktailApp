package fr.nicolaeoctavianmihaila.myapplication.managers

import android.content.Context
import com.google.gson.Gson
import fr.nicolaeoctavianmihaila.myapplication.dataClasses.Drink

class FavoritesManager {
    private val PREFS_NAME = "favorites"
    private val KEY = "favorites_list"

    fun getFavorites(context: Context): List<Drink> {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        val json = sharedPreferences.getString(KEY, null) ?: return emptyList()
        return Gson().fromJson(json, Array<Drink>::class.java).toList()
    }

    fun toggleFavorite(drink: Drink, context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        val currentList = getFavorites(context).toMutableList()

        if (isFavorite(drink, context)) {
            currentList.removeAll { it.idDrink == drink.idDrink }
        } else {
            currentList.add(drink)
        }

        sharedPreferences.edit()
            .putString(KEY, Gson().toJson(currentList))
            .apply()
    }

    fun isFavorite(drink: Drink, context: Context): Boolean {
        return getFavorites(context).any { it.idDrink == drink.idDrink }
    }
}