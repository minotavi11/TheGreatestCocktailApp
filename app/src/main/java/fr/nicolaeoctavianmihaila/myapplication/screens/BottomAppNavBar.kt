package fr.nicolaeoctavianmihaila.myapplication.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import fr.nicolaeoctavianmihaila.myapplication.R
import fr.nicolaeoctavianmihaila.myapplication.TabBarItem

@Composable
fun BottomAppNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Define colors for selected and unselected states to reuse them
    val selectedButtonColor = Color(0xFF5A189A) // A deep purple for selection
    val unselectedButtonColor = Color.Transparent

    ///PROPER FUCKING ROUTING!!!!!!!!!!!!!
    val context = LocalContext.current
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 55.dp)
        .padding(horizontal = 5.dp)
        .background(color = Color.Gray.copy(alpha=0.7f), shape = RoundedCornerShape(100.dp))
        .height(55.dp),
        verticalAlignment = Alignment.CenterVertically

    )
    {
        val isCategorySelected = currentRoute == "CategoriesScreen"

        Button(
            onClick = { navController.navigate("CategoriesScreen") } ,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isCategorySelected) selectedButtonColor else unselectedButtonColor
            )

        )
        {    Column(
            // Center the content horizontally within the Column.
            horizontalAlignment = Alignment.CenterHorizontally,
            // Center the content vertically within the space the Column takes.
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            Icon( painter = painterResource(id = R.drawable.undo),
                contentDescription = "Sample Icon",
                tint = Color.White,
                modifier = Modifier.size(20.dp))
            Text(text = "Categories")
            }

        }
        val isFavoritesSelected = currentRoute == "FavoritesScreen"
        Button(
            onClick = { navController.navigate("FavoritesScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFavoritesSelected) selectedButtonColor else unselectedButtonColor
            )

        )
        {
            Column(
                // Center the content horizontally within the Column.
                horizontalAlignment = Alignment.CenterHorizontally,
                // Center the content vertically within the space the Column takes.
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.undo),
                    contentDescription = "Sample Icon",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
                Text(text = "Favorites")
            }
        }
        val isDetailsSelected = currentRoute == "DetailedCocktailScreen"
        Button(
            onClick = { navController.navigate("DetailedCocktailScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isDetailsSelected) selectedButtonColor else unselectedButtonColor
            )
        )
        { Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        )
        {
            Icon(
                painter = painterResource(id = R.drawable.undo),
                contentDescription = "Sample Icon",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
            Text(text = "Random")
        }
        }


    }

}



