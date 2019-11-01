package Adapters

import Pojo.SingleItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import cora.debs.trendingrepo.R
import kotlinx.android.synthetic.main.single_item.view.*
import java.util.ArrayList

class RepoDataAdapter : RecyclerView.Adapter<RepoDataAdapter.RepoViewHolder>() {

    private var items: ArrayList<SingleItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.single_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        if (!items.isNullOrEmpty()) {
            return items!!.size
        }
        return 0
    }

    fun setItems(items: ArrayList<SingleItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder?.RepoName.text = items?.get(position)?.name
        holder?.RepoDes.text = items?.get(position)?.description
        holder?.RepoOwnerName.text = items?.get(position)?.owner?.name
        holder?.RepoStars.text = items?.get(position)?.stargazersCount.toString()
        loadImage(holder?.RepoOwnerAvatar, items?.get(position)?.owner?.avatarUrl.toString())
    }

    class RepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val RepoName = view.NameTextView
        val RepoDes = view.DescTextView
        val RepoOwnerName = view.repoOwnerTextView
        val RepoStars = view.repoStartsTextView
        val RepoOwnerAvatar = view.repoOwnerImageView
    }

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
}