package com.example.newspaperapplication.model

import com.google.gson.annotations.SerializedName

data class TopHeadlinesResponse(

    @field:SerializedName("totalResults")
	val totalResults: Int? = null,

    @field:SerializedName("articles")
	val articles: List<Article>? = null,

    @field:SerializedName("status")
	val status: String? = null
)
