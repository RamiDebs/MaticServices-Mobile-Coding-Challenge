package Pojo

import com.google.gson.annotations.SerializedName

data class SingleItem(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("full_name")
    val fullName: String = "",
    val owner: ownerObject = ownerObject(),
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("stargazers_count")
    val stargazersCount: Int = 0,
    @SerializedName("watchers_count")
    val watchersCount: Int = 0
)
