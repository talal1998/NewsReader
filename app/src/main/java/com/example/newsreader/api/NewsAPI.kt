package com.example.newsreader.api

import com.example.newsreader.models.TopHeadlines
import com.example.newsreader.utility.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Class to that queries newsapi.org for specific articles
 * @author 956013
 */

interface NewsAPI {
    /** uses the newsapi interface to scrape news for each country*/
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<TopHeadlines>

    /** uses 'everything' url to query entire newsapi database */
    @GET("v2/everything")
    suspend fun getSearchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<TopHeadlines>

    /** uses the newsapi interface to scrape news for each category*/
    @GET("v2/top-headlines")
    suspend fun getSportsNews(
        @Query("category")
        category: String = "sports",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<TopHeadlines>

    @GET("v2/top-headlines")
    suspend fun getEntertainmentNews(
        @Query("category")
        category: String = "entertainment",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<TopHeadlines>

    @GET("v2/top-headlines")
    suspend fun getScienceNews(
        @Query("category")
        category: String = "science",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<TopHeadlines>

    @GET("v2/top-headlines")
    suspend fun getBusinessNews(
        @Query("category")
        category: String = "business",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<TopHeadlines>

    @GET("v2/top-headlines")
    suspend fun getHealthNews(
        @Query("category")
        category: String = "health",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<TopHeadlines>

}