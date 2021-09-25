package com.example.newsreader.models

/**
 * CLass that models Top Headlines response from newsapi.org
 * @author 956013
 */

data class TopHeadlines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)