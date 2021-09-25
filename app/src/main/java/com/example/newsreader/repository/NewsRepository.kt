package com.example.newsreader.repository

import com.example.newsreader.api.RetrofitInstance
import com.example.newsreader.db.ArticleDatabase
import com.example.newsreader.models.Article

/**
 * Class to retrieve data from the API and pass it along to the rest of the app
 */

class NewsRepository(
    val db: ArticleDatabase
) {

    /** functions to get each specific query from news api*/
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun getSearchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.getSearchNews(searchQuery, pageNumber)

    suspend fun getSportsNews(category: String, pageNumber: Int) =
        RetrofitInstance.api.getSportsNews(category, pageNumber)

    suspend fun getEntertainmentNews(category: String, pageNumber: Int) =
        RetrofitInstance.api.getEntertainmentNews(category, pageNumber)

    suspend fun getHealthNews(category: String, pageNumber: Int) =
        RetrofitInstance.api.getHealthNews(category, pageNumber)

    suspend fun getBusinessNews(category: String, pageNumber: Int) =
        RetrofitInstance.api.getBusinessNews(category, pageNumber)

    suspend fun getScienceNews(category: String, pageNumber: Int) =
        RetrofitInstance.api.getScienceNews(category, pageNumber)

    suspend fun upsert(article: Article)  = db.getArticleDao().upsert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}