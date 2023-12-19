package com.kotlinpratice.newsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinpratice.newsapp.R
import com.kotlinpratice.newsapp.modal.Article
import com.kotlinpratice.newsapp.modal.NewsModal

class NewsAdapter(private val item: NewsModal, private val listener: NewsItemCLicked): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(item.articles[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return item.articles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = item.articles.get(position).title
        holder.tvAuthor.text = item.articles.get(position).author
        Glide.with(holder.itemView.context).load(item.articles.get(position).urlToImage)
            .placeholder(R.drawable.no_image)
            .into(holder.ivNewsImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivNewsImage: ImageView = itemView.findViewById(R.id.ivNewsImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvAuthor: TextView = itemView.findViewById(R.id.tvAuthor)
    }

    interface NewsItemCLicked{
        fun onItemClicked(item: Article)
    }

}