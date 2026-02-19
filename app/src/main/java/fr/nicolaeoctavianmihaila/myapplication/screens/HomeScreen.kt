package fr.nicolaeoctavianmihaila.myapplication.screens

import androidx.compose.runtime.Composable

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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable

 fun HomeScreen(modifier: Modifier = Modifier, navController: NavController){

    Column(modifier = Modifier.fillMaxSize()
        .padding(top =20.dp)
        .verticalScroll(rememberScrollState())
    )
    {
        HomeHeader()
        InformationCard()

    }



}


////////////
@Composable
fun  HomeHeader(modifier: Modifier = Modifier){
    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment=(Alignment.CenterHorizontally)
    ) {
        Image(
            painter = painterResource(id = R.drawable.appimage),
            contentDescription = "Cocktail",
            modifier = Modifier
                .fillMaxSize()
                .size(height=275.dp, width=250.dp)
                .clip(CircleShape),

        )
        Text(
            text = "Cocktail App",
            fontSize = 50.sp,
            modifier = Modifier, color=Color.White
        )
    }
}


///////////
@Composable
fun HomeNavigationButtons(modifier: Modifier = Modifier){
    Row(
        modifier = modifier.fillMaxWidth().padding(bottom = 16.dp,), horizontalArrangement=Arrangement.Center
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
fun CenteredLabel(modifier: Modifier = Modifier){
    Row(modifier = modifier.fillMaxWidth(),horizontalArrangement=Arrangement.Center
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
            modifier = modifier
        )
    }

}



/////////
@Composable
fun InformationCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top=16.dp, bottom=10.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(10.dp)
            ),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Blue.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(16.dp)
            .align(Alignment.CenterHorizontally) // center title
        ) {
            Text(
                text = "An app for navigating across your favorite cocktail recipes",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                fontStyle = FontStyle.Italic
            )




        }
    }
}