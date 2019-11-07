package Adapters

import Pojo.SingleItem
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.shashank.sony.fancytoastlib.FancyToast
import cora.debs.trendingrepo.R
import kotlinx.android.synthetic.main.single_item.view.*
import java.util.ArrayList

class RepoDataAdapter : RecyclerView.Adapter<RepoDataAdapter.RepoViewHolder>() {

    private var mRepoItems : ArrayList<SingleItem>? = null

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
        if (!mRepoItems .isNullOrEmpty()) {
            return mRepoItems !!.size
        }
        return 0
    }

    fun setItems (isFirstInsert : Boolean ,mRepoItems : ArrayList<SingleItem>) {
        if (isFirstInsert) {
            this.mRepoItems  = mRepoItems
            notifyDataSetChanged()
        }
        else
        {
            this.mRepoItems?.addAll(mRepoItems )
            this.mRepoItems?.size?.let { notifyItemRangeChanged(it,mRepoItems.size)}
        }

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.RepoName.text = mRepoItems ?.get(position)?.name
        holder.RepoDes.text = mRepoItems ?.get(position)?.description
        holder.RepoOwnerName.text = mRepoItems ?.get(position)?.owner?.name
        holder.RepoStars.text = mRepoItems ?.get(position)?.stargazersCount.toString()
        loadImage(holder.RepoOwnerAvatar, mRepoItems ?.get(position)?.owner?.avatarUrl.toString())
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
            .placeholder(R.drawable.logo)
            .into(imageView)
    }
}