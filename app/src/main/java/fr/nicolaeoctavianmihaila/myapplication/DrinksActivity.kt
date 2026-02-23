package fr.nicolaeoctavianmihaila.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import fr.nicolaeoctavianmihaila.myapplication.screens.DrinksScreen
import fr.nicolaeoctavianmihaila.myapplication.ui.theme.MyApplicationTheme

class DrinksActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val catName = intent.getStringExtra("CATEGORY_NAME") ?: "Cocktail"
            val navController = rememberNavController()
            MyApplicationTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->

                    DrinksScreen(Modifier.padding(innerPadding), navController = navController, categoryName = catName )
                }
            }
        }
    }
}