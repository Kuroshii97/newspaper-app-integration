package com.example.newspaperapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.newspaperapplication.R
import com.example.newspaperapplication.databinding.ActivityHomeBinding
import com.example.newspaperapplication.model.Article
import com.example.newspaperapplication.singleton.SelectHeadlineInterface

import com.example.newspaperapplication.viewmodel.HomeActivityViewModel
import java.io.Serializable

class HomeActivity : AppCompatActivity(), SelectHeadlineInterface {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeActivityViewModel: HomeActivityViewModel
    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        adapter = HomeAdapter(this)
        binding.newsHeadlineRecyclerView.adapter = adapter

        binding.usNewsButton.setOnClickListener {
            homeActivityViewModel.getUSNewsRegion(true)
            getHeadlines()
        }

        binding.idNewsButton.setOnClickListener {
            homeActivityViewModel.getUSNewsRegion(false)
            getHeadlines()
        }

    }

    private fun getHeadlines() {
        if (homeActivityViewModel.usNewsList) {
            homeActivityViewModel.getHeadlines("us")
        } else {
            homeActivityViewModel.getHeadlines("id")
        }
        homeActivityViewModel.topHeadlinesList.observe(this) {
            adapter.updateData(it)
        }
        checkButtonState()
    }


    private fun checkButtonState() {
        if (homeActivityViewModel.usNewsList) {
            binding.usNewsButton.setBackgroundResource(R.drawable.selected_rounded_button)
            binding.idNewsButton.setBackgroundResource(R.drawable.unselected_rounded_button)
        } else {
            binding.usNewsButton.setBackgroundResource(R.drawable.unselected_rounded_button)
            binding.idNewsButton.setBackgroundResource(R.drawable.selected_rounded_button)
        }
    }

    private fun initViewModel() {
        homeActivityViewModel = ViewModelProvider(this).get(HomeActivityViewModel::class.java)
        checkButtonState()
        homeActivityViewModel.topHeadlinesList.observe(this) {
            adapter.updateData(it)
        }
        homeActivityViewModel.getHeadlines("us")
    }

    override fun onSelectArticle(article: Article) {
        homeActivityViewModel.selectHeadline(article)
        val intent = Intent(this, NewsDetailActivity::class.java).putExtra("article", article as Serializable)
        startActivity(intent)
    }

}