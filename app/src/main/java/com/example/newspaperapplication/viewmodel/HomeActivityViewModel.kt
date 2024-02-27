package com.example.newspaperapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newspaperapplication.model.Article
import com.example.newspaperapplication.model.TopHeadlinesResponse
import com.example.newspaperapplication.retrofit.RestApiService
import com.example.newspaperapplication.retrofit.TopHeadlinesRequestResult
import retrofit2.Response

class HomeActivityViewModel: ViewModel() {
    // handled data inside view model
    private var _topHeadlinesList = MutableLiveData<List<Article>>()

    // data for showing in views
    var topHeadlinesList: LiveData<List<Article>> = _topHeadlinesList
    var article: Article = Article()
    var usNewsList: Boolean = true

    fun getHeadlines(country:String){
        val service = RestApiService()
        service.getTopHeadlines(country, object : TopHeadlinesRequestResult {
            override fun onSuccess(response: Response<TopHeadlinesResponse>) {
                if(response.isSuccessful){
                    val headlinesResponse = response.body()
                    _topHeadlinesList.postValue(headlinesResponse!!.articles!!)
                } else {
                    throw Throwable(response.errorBody().toString())
                }
            }

            override fun onFailure(t: Throwable) {
                _topHeadlinesList.postValue(null)
            }
        })
    }

    fun selectHeadline(article: Article){
        this.article = article
    }

    fun getUSNewsRegion(usRegion: Boolean){
        usNewsList = usRegion
    }


}