package fr.nicolaeoctavianmihaila.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.nicolaeoctavianmihaila.myapplication.dataClasses.Drink
import fr.nicolaeoctavianmihaila.myapplication.screens.CocktailScreen
import fr.nicolaeoctavianmihaila.myapplication.ui.theme.MyApplicationTheme


class DetailCocktailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val drinkId = intent.getStringExtra("DRINK_ID")
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CocktailScreen(Modifier.padding(innerPadding), drinkId = drinkId)
                }
            }
        }
    }
}