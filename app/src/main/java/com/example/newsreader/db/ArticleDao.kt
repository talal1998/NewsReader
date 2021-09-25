package com.example.newsreader.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsreader.models.Article

/**
 * Class used to create a Database access object to add, delete and display articles saved to a database
 * @author 956013
 */

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}