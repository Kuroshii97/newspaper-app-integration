package com.example.newspaperapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newspaperapplication.R
import com.example.newspaperapplication.databinding.ActivityNewsDetailBinding
import com.example.newspaperapplication.model.Article
import com.example.newspaperapplication.viewmodel.HomeActivityViewModel

class NewsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsDetailBinding
    private lateinit var article: Article
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        article = intent.extras!!.get("article") as Article

        binding.articleTitleTextView.text = article.title
        binding.articleContentTextView.text = article.content
        Glide.with(this)
            .load(article.urlToImage.toString())
            .placeholder(R.drawable.news_api_logo)
            .into(binding.articleHeaderImageView)
    }

}