package com.example.newspaperapplication.retrofit

import com.example.newspaperapplication.model.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    fun requestTopHeadlines(@Query("country") countryCode:String): Call<TopHeadlinesResponse>

}