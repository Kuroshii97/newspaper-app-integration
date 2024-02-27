package com.example.newspaperapplication.retrofit

import com.example.newspaperapplication.model.TopHeadlinesResponse
import retrofit2.Response

interface TopHeadlinesRequestResult{
    fun onSuccess(response: Response<TopHeadlinesResponse>)
    fun onFailure(t: Throwable)
}