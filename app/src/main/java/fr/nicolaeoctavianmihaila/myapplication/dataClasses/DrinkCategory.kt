package fr.nicolaeoctavianmihaila.myapplication.dataClasses

import com.google.gson.annotations.SerializedName

data class DrinkCategory(
    @SerializedName("strCategory") val strCategory: String?
)