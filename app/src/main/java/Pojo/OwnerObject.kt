package Pojo

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import cora.debs.trendingrepo.R

data class ownerObject(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("node_id")
    val nodeId: String = "",
    @SerializedName("avatar_url")
    val avatarUrl: String = "",
    @SerializedName("login")
    val name: String = ""
)

@BindingAdapter("avatar_url")
fun loadImage(imageView: ImageView, imageURL: String) {

    Glide.with(imageView.context)
        .setDefaultRequestOptions(
            RequestOptions()
                .circleCrop()
        )
        .load(imageURL)
        .placeholder(R.drawable.loading)
        .into(imageView)
}