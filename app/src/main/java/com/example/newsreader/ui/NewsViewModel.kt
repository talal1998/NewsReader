package com.example.newsreader.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsreader.models.Article
import com.example.newsreader.models.TopHeadlines
import com.example.newsreader.repository.NewsRepository
import com.example.newsreader.utility.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * Class that creates viewmodels for each article and handles responses from newsapi.org
 * @author 956013
 */

class NewsViewModel(
    private val newsRepository: NewsRepository
): ViewModel() {

    val breakingNews: MutableLiveData<Resource<TopHeadlines>> = MutableLiveData()
    private var breakingNewsPage = 1

    val searchNews: MutableLiveData<Resource<TopHeadlines>> = MutableLiveData()
    private var searchNewsPage = 1

    val sportsNews: MutableLiveData<Resource<TopHeadlines>> = MutableLiveData()
    private var sportsNewsPage = 1

    val businessNews: MutableLiveData<Resource<TopHeadlines>> = MutableLiveData()
    private var businessNewsPage = 1

    val scienceNews: MutableLiveData<Resource<TopHeadlines>> = MutableLiveData()
    private var scienceNewsPage = 1

    val entertainmentNews: MutableLiveData<Resource<TopHeadlines>> = MutableLiveData()
    private var entertainmentNewsPage = 1

    val healthNews: MutableLiveData<Resource<TopHeadlines>> = MutableLiveData()
    private var healthNewsPage = 1

    /**
     * makes requests to the api for countries and categories
     */
    init {
        getBreakingNews("gb")
        getSportsNews("sports")
        getBusinessNews("business")
        getScienceNews("science")
        getHealthNews("health")
        getEntertainmentNews("entertainment")
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }

    /** function to request breaking news from the api using the country code as a parameter */
    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    /** functions to request specific news from the api using the category as a parameter */
    private fun getSportsNews(category: String) = viewModelScope.launch {
        sportsNews.postValue(Resource.Loading())
        val response = newsRepository.getSportsNews(category, sportsNewsPage)
        sportsNews.postValue(handleSportsNewsResponse(response))
    }

    private fun getBusinessNews(category: String) = viewModelScope.launch {
        businessNews.postValue(Resource.Loading())
        val response = newsRepository.getBusinessNews(category, businessNewsPage)
        businessNews.postValue(handleBusinessNewsResponse(response))
    }

    private fun getScienceNews(category: String) = viewModelScope.launch {
        scienceNews.postValue(Resource.Loading())
        val response = newsRepository.getScienceNews(category, scienceNewsPage)
        scienceNews.postValue(handleScienceNewsResponse(response))
    }

    private fun getEntertainmentNews(category: String) = viewModelScope.launch {
        entertainmentNews.postValue(Resource.Loading())
        val response = newsRepository.getEntertainmentNews(category, entertainmentNewsPage)
        entertainmentNews.postValue(handleEntertainmentNewsResponse(response))
    }

    private fun getHealthNews(category: String) = viewModelScope.launch {
        healthNews.postValue(Resource.Loading())
        val response = newsRepository.getHealthNews(category, healthNewsPage)
        healthNews.postValue(handleHealthNewsResponse(response))
    }

    fun getSearchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.getSearchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    /** functions to handle response from api after each query */
    private fun handleBreakingNewsResponse(response: Response<TopHeadlines>) : Resource<TopHeadlines> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSportsNewsResponse(response: Response<TopHeadlines>) : Resource<TopHeadlines> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleBusinessNewsResponse(response: Response<TopHeadlines>) : Resource<TopHeadlines> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleScienceNewsResponse(response: Response<TopHeadlines>) : Resource<TopHeadlines> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleEntertainmentNewsResponse(response: Response<TopHeadlines>) : Resource<TopHeadlines> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleHealthNewsResponse(response: Response<TopHeadlines>) : Resource<TopHeadlines> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<TopHeadlines>) : Resource<TopHeadlines> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}