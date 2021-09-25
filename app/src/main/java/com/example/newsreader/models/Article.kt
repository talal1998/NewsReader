package com.example.newsreader.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Class that models an Article object.
 * @author 956013
 */

@Entity(
    tableName = "articles"
)
data class Article(
    /** response objects for each article object in newsapi.org*/
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String? = null,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable