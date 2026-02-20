package fr.nicolaeoctavianmihaila.myapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.nicolaeoctavianmihaila.myapplication.R
import android.widget.Toast
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import fr.nicolaeoctavianmihaila.myapplication.dataClasses.Drink
import fr.nicolaeoctavianmihaila.myapplication.network.NetworkManager


@Composable
    fun CocktailScreen(modifier: Modifier = Modifier, drink:Drink ){
    val context = LocalContext.current
    var drinkState by remember { mutableStateOf<Drink?>(null) }

    // 2. Launch the network call
    LaunchedEffect(Unit) {
        if (drinkState == null) {
            try {
                val response = NetworkManager.apiService.GetRandomCocktail()
                drinkState = response.drinks?.firstOrNull()
            } catch (e: Exception) {
                e.printStackTrace() // Log errors to Logcat
            }
        }
    }
        val currentDrink = drinkState
        if (currentDrink == null) {

            Toast.makeText(
                context,
                "Loading Random Cocktail...",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(top = 50.dp, start = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState())
            )
            {
                CocktailHeader(currentDrink)
                CocktailCategoryButtons(currentDrink)
                CocktailGlassInfo(currentDrink)
                IngredientsCard(currentDrink)
                PreparationCard(currentDrink)
            }
        }
    }



////////////
@Composable
fun  CocktailHeader(drink: Drink){
    Column(modifier = Modifier.fillMaxWidth(),   horizontalAlignment=(Alignment.CenterHorizontally)
    ) {
        AsyncImage(
            model=drink.strDrinkThumb,
            contentDescription = drink.strDrink,
            placeholder = painterResource(id = R.drawable.wine),
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = drink.strDrink ?: "Unknown Drink",
            fontSize = 35.sp,
            modifier = Modifier, color=Color.White
        )
    }
}


///////////
@Composable
fun CocktailCategoryButtons(drink: Drink){
    Row(
         Modifier
             .fillMaxWidth()
             .padding(bottom = 16.dp,), horizontalArrangement=Arrangement.Center
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RectangleShape,
            modifier = Modifier
                .padding(5.dp)
                .size(150.dp, 45.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tag),
                contentDescription = "Sample Icon",
                tint = Color.White,
                modifier = Modifier.size(15.dp)
            )

            Text(text = "Other/Unknown", fontSize = 12.sp, color = Color.White)

        }

        Button(
            onClick = { /*TODO*/ },
            shape = RectangleShape,
            modifier = Modifier
                .padding(5.dp)
                .size(150.dp, 45.dp)
                .clip(RoundedCornerShape(24.dp))
        ) {
                Icon(
                    painter = painterResource(id = R.drawable.drink),
                    contentDescription = "Sample Icon",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            Text(text = "Non-Alcoholic", fontSize = 12.sp, color = Color.White)
        }



    }

}


/////////
@Composable
fun CocktailGlassInfo(drink: Drink){
    Row(Modifier.fillMaxWidth(),horizontalArrangement=Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.warmdrink),
            contentDescription = "Sample Icon",
            tint = Color.White,
            modifier = Modifier.size(15.dp)
        )

        Text(
            text = "Special Wine Glass",
            fontSize = 20.sp,
            fontWeight = FontWeight.Thin,
            fontStyle = FontStyle.Italic,
            color=Color.White,
        )
    }

}



/////////
@Composable
fun IngredientsCard(drink: Drink) {
    Card(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, bottom = 10.dp)
            .border(
                width = 3.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.7f))
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally) // center title
        ) {
            Text(
                text = "Ingredients",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
            )

            Spacer(modifier = Modifier.height(16.dp)) // spacing between title and rows
            drink.ingredientList().forEach { (ingredient, measure) ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)) {
                    Text(
                        text = ingredient, // Now this reference is resolved!
                        modifier = Modifier.weight(1f),
                        color = Color.White,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic
                    )
                    Text(
                        text = measure, // Now this reference is resolved!
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            }
        }
    }


@Composable
fun PreparationCard(drink: Drink) {
    Card(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 16.dp, bottom = 10.dp)
            .border(
                width = 3.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(alpha = 0.7f))
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .align(Alignment.CenterHorizontally)) {
            Text(
                text = "Preparation",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(text = drink.strInstructions ?: "No instructions provided.")
            }
        }
}




