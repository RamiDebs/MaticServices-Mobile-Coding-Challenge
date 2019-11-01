package Pojo

import com.google.gson.annotations.SerializedName

data class ownerObject(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("login")
    val name: String = ""
)
