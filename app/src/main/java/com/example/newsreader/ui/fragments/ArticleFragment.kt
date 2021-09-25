package com.example.newsreader.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsreader.R
import com.example.newsreader.ui.NewsActivity
import com.example.newsreader.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.*
import java.lang.Exception

/**
 * Class to  display each Article in webview, within a fragment
 */

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    val TAG = "ArticleFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            super.onViewCreated(view, savedInstanceState)
            viewModel = (activity as NewsActivity).viewModel
            val article = args.article
            /** loads the article in a web view within the app and not in a separate browser*/
            webView.apply {
                webViewClient = WebViewClient()
                loadUrl(article.url)
            }
            fab.setOnClickListener {
                viewModel.saveArticle(article)
                Snackbar.make(view, "Article saved.", Snackbar.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "OnCreateView", e)
        }
    }
}