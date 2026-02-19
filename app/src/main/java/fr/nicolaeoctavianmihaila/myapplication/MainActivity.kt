    package fr.nicolaeoctavianmihaila.myapplication

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
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                var nameScreen = when (currentRoute) {
                    Routes.Home -> "Home"
                    Routes.Categories -> "Categories"
                    Routes.Favorites -> "Favorites"
                    Routes.Details -> "Drink Details"
                    Routes.Drinks -> "Drinks"
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

                                        if (currentRoute == Routes.Details) {
                                        IconButton(
                                            onClick = { /*TODO*/ },
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .size(50.dp)
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.undo),
                                                contentDescription = "Sample Icon",
                                                tint = Color.White,
                                                modifier = Modifier.size(40.dp)
                                            )
                                        }

                                            IconButton(
                                                onClick = {
                                                    Toast.makeText(
                                                        context,
                                                        "Added to Favorites!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                },
                                                modifier = Modifier
                                                    .padding(5.dp)
                                                    .size(50.dp)
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.favorite),
                                                    contentDescription = "Sample Icon",
                                                    tint = Color.White,
                                                    modifier = Modifier.size(40.dp)
                                                )
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
                                    composable(Routes.Details){
                                        CocktailScreen(modifier = Modifier)
                                    }
                                    composable(Routes.Drinks){
                                        DrinksScreen(modifier = Modifier, navController=navController)
                                    }
                                    composable(Routes.Favorites){
                                        FavoritesScreen(modifier = Modifier)
                                    }

                                })

                            }
                        }

                    }
                }
            }
        }
    }






