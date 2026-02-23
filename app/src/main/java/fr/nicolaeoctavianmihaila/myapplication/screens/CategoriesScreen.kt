package fr.nicolaeoctavianmihaila.myapplication.screens

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.nicolaeoctavianmihaila.myapplication.dataClasses.DrinkCategory
import fr.nicolaeoctavianmihaila.myapplication.network.NetworkManager
import fr.nicolaeoctavianmihaila.myapplication.ui.theme.Routes

@Composable
fun CategoriesScreen(modifier: Modifier, navController: NavController) {
    var categories by remember { mutableStateOf<List<DrinkCategory>>(emptyList()) }
// SHOW PROPER PRODUCTS BASED ON CATEGORY
    LaunchedEffect(Unit) { //AI recommended me to use this, not sure why
        try {
            val response = NetworkManager.apiService.getCategories()
            categories = response.drinks ?: emptyList()
        } catch (e: Exception) { e.printStackTrace() }
    }
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(categories) { category ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        val encodedCategory = Uri.encode(category.strCategory)
                        navController.navigate("${Routes.Drinks}/$encodedCategory")
                    //navigates to a specific category
                    }
            ) {
                Text(category.strCategory ?: "", Modifier.padding(16.dp))
            }
        }
    }
}
