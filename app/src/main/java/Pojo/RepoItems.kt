package Pojo

import com.google.gson.annotations.SerializedName

data class RepoItems (
    @SerializedName("items")
    val items: List<SingleItem> = listOf()
    )