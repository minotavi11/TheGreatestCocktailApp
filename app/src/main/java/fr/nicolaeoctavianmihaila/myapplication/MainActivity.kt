    package fr.nicolaeoctavianmihaila.myapplication

    import android.net.Uri
    import android.os.Bundle
    import android.widget.Toast
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.activity.enableEdgeToEdge
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Favorite
    import androidx.compose.material.icons.filled.FavoriteBorder
    import androidx.compose.material.icons.filled.Home
    import androidx.compose.material.icons.filled.Menu
    import androidx.compose.material.icons.outlined.Favorite
    import androidx.compose.material.icons.outlined.Home
    import androidx.compose.material.icons.outlined.Menu
    import androidx.compose.material3.ExperimentalMaterial3Api
    import androidx.compose.material3.Icon
    import androidx.compose.material3.IconButton
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.material3.TopAppBar
    import androidx.compose.material3.TopAppBarDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Brush
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.vector.ImageVector
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.tooling.preview.Preview
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.compose.NavHost
    import androidx.navigation.compose.composable
    import androidx.navigation.compose.currentBackStackEntryAsState
    import androidx.navigation.compose.rememberNavController
    import fr.nicolaeoctavianmihaila.myapplication.dataClasses.Drink
    import fr.nicolaeoctavianmihaila.myapplication.managers.FavoritesManager
    import fr.nicolaeoctavianmihaila.myapplication.screens.BottomAppNavBar
    import fr.nicolaeoctavianmihaila.myapplication.screens.CategoriesScreen
    import fr.nicolaeoctavianmihaila.myapplication.screens.CocktailScreen
    import fr.nicolaeoctavianmihaila.myapplication.screens.DrinksScreen
    import fr.nicolaeoctavianmihaila.myapplication.screens.FavoritesScreen
    import fr.nicolaeoctavianmihaila.myapplication.screens.HomeScreen
    import fr.nicolaeoctavianmihaila.myapplication.ui.theme.MyApplicationTheme
    import fr.nicolaeoctavianmihaila.myapplication.ui.theme.Routes

    data class TabBarItem(
        val title: String,
        val selectedIcon: ImageVector,
        val unselectedIcon: ImageVector
    )
    class MainActivity : ComponentActivity() {
        @OptIn(ExperimentalMaterial3Api::class)
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContent {
                // navigation stuff
                val navController = rememberNavController()
                val favoritesManager = FavoritesManager()
                val context = LocalContext.current

                var currentDrinkInView by remember { mutableStateOf<Drink?>(null) }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val drinkId = navBackStackEntry?.arguments?.getString("drinkId")
                val currentRoute = navBackStackEntry?.destination?.route


                // screen checking
                val isOnDetailsScreen = currentRoute?.startsWith(Routes.Details) == true
                val isRandomMode = isOnDetailsScreen && drinkId == "random"
                val isSpecificDrink = isOnDetailsScreen && drinkId != null && drinkId != "random"

                var nameScreen = when  {
                    currentRoute == Routes.Home -> "Home"
                    currentRoute == Routes.Categories -> "Categories"
                    currentRoute == Routes.Favorites -> "Favorites"
                    isRandomMode -> "Random Cocktail"
                    isSpecificDrink -> "Drink Details"
                    currentRoute?.startsWith(Routes.Drinks) == true -> "Drinks"
                    else -> "Cocktail App"
                }

                MyApplicationTheme {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF1E3A8A),
                                        Color(0xFF9D4EDD),
                                        Color(0xFF5A189A)
                                    )
                                )
                            )

                    ) {
                        Scaffold(
                            containerColor = Color.Transparent,
                            topBar = {
                                val context = LocalContext.current
                                TopAppBar(
                                    colors = TopAppBarDefaults.topAppBarColors(
                                        containerColor = Color.Transparent
                                    ),
                                    title = {
                                        Text(
                                            text = "$nameScreen",
                                            fontSize = 30.sp,
                                            modifier = Modifier, color = Color.White
                                        )
                                    },
                                    actions = {
                                        IconButton(
                                            onClick = { navController.navigate("HomeScreen") },
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .size(50.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.menu),
                                                contentDescription = "Sample Icon",
                                                tint = Color.White,
                                                modifier = Modifier.size(40.dp)
                                            )
                                        }

                                        if (isOnDetailsScreen) {
                                            IconButton(
                                                onClick = {
                                                    navController.popBackStack()
                                                },
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .size(50.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.undo),
                                                    contentDescription = "Back",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(40.dp)
                                                )
                                            }

                                            currentDrinkInView?.let { drink ->
                                                IconButton(
                                                    onClick = {
                                                        favoritesManager.toggleFavorite(drink, context)
                                                        currentDrinkInView = drink.copy()
                                                        Toast.makeText(context, "Favorites updated!", Toast.LENGTH_SHORT).show()
                                                    }
                                                ) {
                                                    val isFav = favoritesManager.isFavorite(drink, context)
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.favorite),
                                                        contentDescription = "Favorite",
                                                        tint = if (isFav) Color.Red else Color.White, // is favorited
                                                        modifier = Modifier.size(40.dp)
                                                    )
                                                }
                                            }
                                        }

                                    })
                                        //weird ass reference code for the top app bar xD
//                                TopAppBar({
//                                Text("Cocktail App")
//                                }, actions = {
//                                  IconButton({
//                                    Toast
//                                        .makeText(context, "Added to favorite", Toast.LENGTH_LONG)
//                                        .show()
//                                     }) {
//                                    Icon(
//                                        imageVector = Icons.Filled.FavoriteBorder,
//                                        contentDescription = "Localized description"
//                                            )
//                                         }
//                                     })


                            },
                            bottomBar = { BottomAppNavBar(navController) }
                        ) { innerPadding ->
                            Box(modifier = Modifier.padding(innerPadding)) {


                                NavHost(navController = navController, startDestination = Routes.Home, builder ={
                                    composable(Routes.Home){
                                        HomeScreen(modifier = Modifier, navController = navController)
                                    }

                                    composable(Routes.Categories){
                                        CategoriesScreen(modifier = Modifier, navController = navController)
                                    }
                                    composable("${Routes.Details}/{drinkId}") { backStackEntry ->

                                        val drinkId = backStackEntry.arguments?.getString("drinkId")

                                        CocktailScreen(
                                            modifier = Modifier,
                                            drinkId = drinkId,
                                            onDrinkLoaded = { drink -> currentDrinkInView = drink } // get the drink
                                        )
                                    }
                                    composable("${Routes.Drinks}/{categoryName}"){
                                        backStackEntry ->
                                        val catName = backStackEntry.arguments?.getString("categoryName") ?: "Cocktail"
                                        val decodedCatName = Uri.decode(catName)
                                        DrinksScreen(modifier = Modifier, navController=navController, categoryName = decodedCatName)
                                    }
                                    composable(Routes.Favorites){
                                        FavoritesScreen(modifier = Modifier, navController = navController)
                                    }

                                })

                            }
                        }

                    }
                }
            }
        }
    }