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
fun DrinksScreen(modifier: Modifier, navController: NavController) {
    val list = listOf("Manhattan", "Pina Colada")

    LazyColumn(
        modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { drink ->
            Card(
                Modifier.clickable {
                    // Navigate to the details screen
                    navController.navigate(Routes.Details)
                }
            ) {
                Text(
                    text = drink,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}
