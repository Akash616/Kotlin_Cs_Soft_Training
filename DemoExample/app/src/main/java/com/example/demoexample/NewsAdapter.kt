package com.example.demoexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(private val item: List<Article>, private val listener: NewsItemCLicked): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = ViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(item[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = item.get(position).title
        holder.tvAuthor.text = item.get(position).author
        Picasso.get().load(item.get(position).urlToImage)
            .placeholder(R.drawable.ic_launcher_background)
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