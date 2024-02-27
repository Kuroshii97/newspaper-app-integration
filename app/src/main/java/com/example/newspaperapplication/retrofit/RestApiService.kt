package com.example.newspaperapplication.retrofit

import com.example.newspaperapplication.model.TopHeadlinesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestApiService {
    private var retrofitClient = RetrofitClient()

    fun getTopHeadlines(country: String, topHeadlinesRequestResult: TopHeadlinesRequestResult) {
        val retrofit = retrofitClient.buildService(ApiService::class.java)
        retrofit.requestTopHeadlines(country).enqueue(object : Callback<TopHeadlinesResponse> {
            override fun onResponse(
                call: Call<TopHeadlinesResponse>, response: Response<TopHeadlinesResponse>
            ) {
                topHeadlinesRequestResult.onSuccess(response)
            }

            override fun onFailure(call: Call<TopHeadlinesResponse>, t: Throwable) {
                topHeadlinesRequestResult.onFailure(t)
            }
        })
    }


}