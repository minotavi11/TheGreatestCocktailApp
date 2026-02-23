package fr.nicolaeoctavianmihaila.myapplication.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import fr.nicolaeoctavianmihaila.myapplication.managers.FavoritesManager
import fr.nicolaeoctavianmihaila.myapplication.ui.theme.Routes

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val favoritesManager = FavoritesManager()
    val favorites = remember { favoritesManager.getFavorites(context) }

    if (favorites.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No favorites yet!", color = Color.White, fontSize = 20.sp)
        }
    } else {
        LazyColumn(modifier = modifier.fillMaxSize().padding(16.dp)) {
            items(favorites) { drink ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { navController.navigate("${Routes.Details}/${drink.idDrink}") },
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.1f))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)) {
                        AsyncImage(
                            model = drink.strDrinkThumb,
                            contentDescription = null,
                            modifier = Modifier.size(60.dp).clip(CircleShape)
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(drink.strDrink ?: "", color = Color.White, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}