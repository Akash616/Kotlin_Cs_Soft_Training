package com.kotlinpratice.socialmediaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.kotlinpratice.socialmediaapp.R
import com.kotlinpratice.socialmediaapp.Utils
import com.kotlinpratice.socialmediaapp.models.Post

class RvPostAdapter(options: FirestoreRecyclerOptions<Post>, val listener: IPostAdapter) : FirestoreRecyclerAdapter<Post, RvPostAdapter.PostViewHolder>(options)
{

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivUserImage: ImageView = itemView.findViewById(R.id.ivUserImage)
        val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        val tvCreatedAt: TextView = itemView.findViewById(R.id.tvCreatedAt)
        val tvPostTitle: TextView = itemView.findViewById(R.id.tvPostTitle)
        val ivLikeBtn: ImageView = itemView.findViewById(R.id.ivLikeBtn)
        val tvLikeCount: TextView = itemView.findViewById(R.id.tvLikeCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rvitem_post, parent, false)
        val postViewHolder = PostViewHolder(view)

        postViewHolder.ivLikeBtn.setOnClickListener {
            listener.onLikeClicked(snapshots.getSnapshot(postViewHolder.adapterPosition).id)
        }

        return postViewHolder
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int, model: Post) {
        holder.tvPostTitle.text = model.text
        holder.tvUserName.text = model.createdBy.displayName
        holder.tvLikeCount.text = model.likedBy.size.toString()
        Glide.with(holder.ivUserImage.context).load(model.createdBy.imageUrl).circleCrop().into(holder.ivUserImage)

        holder.tvCreatedAt.text = Utils.getTimeAgo(model.createdAt)

        val auth = Firebase.auth
        val currentUserId = auth.currentUser!!.uid
        val isLiked = model.likedBy.contains(currentUserId)
        if (isLiked){
            holder.ivLikeBtn.setImageDrawable(ContextCompat.getDrawable(holder.ivLikeBtn.context, R.drawable.ic_liked))
        }else{
            holder.ivLikeBtn.setImageDrawable(ContextCompat.getDrawable(holder.ivLikeBtn.context, R.drawable.ic_unliked))
        }

    }

}

interface IPostAdapter{
    fun onLikeClicked(postId: String)
}