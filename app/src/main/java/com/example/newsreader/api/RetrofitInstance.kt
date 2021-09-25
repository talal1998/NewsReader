package com.example.newsreader.api

import com.example.newsreader.utility.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that creates an instance of Retrofit to send network requests to newsapi.org
 * @author 956013
 */

/** Retrieved from: */
/***************************************************************************************
 *    Title: Retrofit setup
 *    Author: Philipp Lackner
 *    Date: 12-04-2020
 *    Availability: https://github.com/philipplackner/MVVMNewsApp/tree/Retrofit-setup
 *
 ***************************************************************************************/

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}