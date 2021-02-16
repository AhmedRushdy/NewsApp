package com.graduationproject.newsapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil

import com.graduationproject.newsapp.databinding.ItemNewsBinding
import com.graduationproject.newsapp.models.Article
import com.squareup.picasso.Picasso

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView
    .ViewHolder(binding.root)



    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return newItem.url == oldItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return newItem == oldItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]

             holder.binding.apply {

                 articleTitle.text = article.title
                 describtion.text = article.description
                 date.text = article.publishedAt
                 source.text = article.source?.name
                 Picasso.get().load(article.urlToImage).into(ivNews)


                 }
                holder.itemView.apply {
                    setOnClickListener {
                        onItemClickListener?.let { it(article) }
                    }

             }
         }


    private var onItemClickListener:((Article) -> Unit)? = null

    fun setOnItemClickListener(listener:(Article)->Unit){
        onItemClickListener = listener
    }

}