package fr.nicolaeoctavianmihaila.myapplication.network

import fr.nicolaeoctavianmihaila.myapplication.dataClasses.*
import retrofit2.Call //TAKE THE CODE OF RETROFIT
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random.php")
    suspend fun GetRandomCocktail():CocktailResponse //we use the data class

    @GET(value = "list.php?c=list")
    suspend fun getCategories():CategoryListResponse

    @GET(value = "filter.php") //filter.php?c=YOUR_CATEGORY
    suspend fun getDrinkType(@Query("c") categoryID: String = "list"): CocktailResponse

    @GET(value ="lookup.php") //lookup.php?i=YOUR_DRINK_IDENTIFIER
   suspend fun getDrinkDetails(@Query("i") drinkID: String):DrinkFilterResponse

}