package fr.nicolaeoctavianmihaila.myapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.nicolaeoctavianmihaila.myapplication.ui.theme.Routes

@Composable
fun CategoriesScreen(modifier: Modifier, navController: NavController) {
    val list = listOf("Beer", "Cocktail", "Coffee")

    LazyColumn(
        modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { category ->

            Card(
                Modifier.clickable {
                    if(list.contains("Beer"))
                     navController.navigate(Routes.Drinks)
                    else if(list.contains("Cocktail"))
                        navController.navigate(Routes.Details)
                }
            ) {
                Text(
                    text = category,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
