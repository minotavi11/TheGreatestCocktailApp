package fr.nicolaeoctavianmihaila.myapplication.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import fr.nicolaeoctavianmihaila.myapplication.R

enum class Category {
    //TO DO - add more customization for each category of drink
    BEER, COCKTAIL, COCOA, COFFEE, LIQUOR,
    DRINK, PUNCH, SHAKE, SHOT, SOFT,
    ALCOHOLIC, NON_ALCOHOLIC, OTHER;

    companion object {
        // display name
        fun toDisplayString(category: Category): String {
            return when(category) {
                ALCOHOLIC -> "Alcoholic"
                NON_ALCOHOLIC -> "Non-Alcoholic"
                LIQUOR -> "Homemade Liquor"
                DRINK -> "Ordinary Drink"
                SOFT -> "Soft Drink"
                else -> category.name.lowercase().replaceFirstChar { it.uppercase() }
            }
        }

        // colors the name of the category
        @Composable
        fun getGradientColors(category: Category): List<Color> {
            return when(category) {
                ALCOHOLIC, NON_ALCOHOLIC -> listOf(
                    colorResource(R.color.orange_200),
                    colorResource(R.color.orange_700)
                )
                else -> listOf(
                    colorResource(R.color.teal_200),
                    colorResource(R.color.teal_700)
                )
            }
        }
    }
}