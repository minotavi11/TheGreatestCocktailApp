package fr.nicolaeoctavianmihaila.myapplication.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import fr.nicolaeoctavianmihaila.myapplication.DetailCocktailActivity
import fr.nicolaeoctavianmihaila.myapplication.dataClasses.DrinkPreview
import fr.nicolaeoctavianmihaila.myapplication.network.NetworkManager
import fr.nicolaeoctavianmihaila.myapplication.ui.theme.Routes


@Composable
fun DrinksScreen(modifier: Modifier, navController: NavController, categoryName: String) {
    var drinks by remember { mutableStateOf<List<DrinkPreview>>(emptyList()) }
    val context= LocalContext.current
    LaunchedEffect(categoryName) {
        try {
            val response = NetworkManager.apiService.getDrinkType(categoryName)
            drinks = response.drinks ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    LazyColumn(
        modifier.fillMaxSize()
    ) {
        items(drinks) { drink ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("${Routes.Details}/${drink.idDrink}")
                    }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = drink.strDrinkThumb,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp))
                    )
                    Text(text = drink.strDrink ?: "", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}
