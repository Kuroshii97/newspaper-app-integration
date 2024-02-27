package com.example.newspaperapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newspaperapplication.R
import com.example.newspaperapplication.databinding.NewsCardItemBinding
import com.example.newspaperapplication.model.Article
import com.example.newspaperapplication.singleton.SelectHeadlineInterface

class HomeAdapter(private val selectHeadLineInterface: SelectHeadlineInterface) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var articleList: List<Article> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        return HomeViewHolder(
            NewsCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeAdapter.HomeViewHolder, position: Int) {
        val article = articleList[position]
        holder.bindItem(article)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    inner class HomeViewHolder(val itemBinding: NewsCardItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindItem(article: Article) {
            itemBinding.newsCardHeaderTextView.text = article.title
            Glide.with(itemBinding.newsCardHeaderImageView.context)
                .load(article.urlToImage.toString()).placeholder(R.drawable.news_api_logo)
                .into(itemBinding.newsCardHeaderImageView)
            itemBinding.newsCardView.setOnClickListener {
                selectArticle(article)
            }
        }
    }

    fun updateData(newArticleList: List<Article>) {
        articleList = newArticleList
        notifyDataSetChanged()
    }

    fun selectArticle(article: Article) {
        selectHeadLineInterface.onSelectArticle(article);
    }


}