package com.example.newsreader.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsreader.models.Article

/**
 * Creates Database to store Article objects to using DAO
 * @author 956013
 */

/** Retrieved from: */
/***************************************************************************************
 *    Title: Room database
 *    Author: Philipp Lackner
 *    Date: 15-04-2020
 *    Availability: https://github.com/philipplackner/MVVMNewsApp/tree/Room-database-and-typeconverter
 *
 ***************************************************************************************/

@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()

    }
}