package com.example.newsreader.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsreader.repository.NewsRepository

/**
 * Class that creates view models using NewsViewModel
 * @author 956013
 */

/** Retrieved from: */
/***************************************************************************************
 *    Title: Architecture skeleton
 *    Author: Philipp Lackner
 *    Date: 15-04-2020
 *    Availability: https://github.com/philipplackner/MVVMNewsApp/tree/Architecture-skeleton
 *
 ***************************************************************************************/

class NewsViewModelProviderFactory(
    val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}