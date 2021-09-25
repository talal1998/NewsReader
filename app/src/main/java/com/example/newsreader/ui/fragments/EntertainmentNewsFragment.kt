package com.example.newsreader.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsreader.R
import com.example.newsreader.adapters.ArticleAdapter
import com.example.newsreader.ui.NewsActivity
import com.example.newsreader.ui.NewsViewModel
import com.example.newsreader.utility.Resource
import kotlinx.android.synthetic.main.fragment_entertainment.*

/**
 * Class that displays entertainment news within a fragment
 * @author 956013
 */

class EntertainmentNewsFragment :Fragment(R.layout.fragment_entertainment) {
    lateinit var viewModel: NewsViewModel
    lateinit var articleAdapter: ArticleAdapter

    val TAG = "EntertainmentNewsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            super.onViewCreated(view, savedInstanceState)
            viewModel = (activity as NewsActivity).viewModel
            setUpRecyclerView()

            /** transitions to the article fragment once the card layout is tapped on*/
            articleAdapter.setOnItemClickListener {
                val bundle = Bundle().apply {
                    putSerializable("article", it)
                }
                findNavController().navigate(
                    R.id.action_entertainmentFragment_to_articleFragment,
                    bundle
                )
            }
            viewModelObservable()
        } catch (e: Exception) {
            Log.e(TAG, "onCreateView", e)
            throw e
        }

    }

    /** observes LiveData, checks if api request was a success and displays articles */
    private fun viewModelObservable(){
        viewModel.entertainmentNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        articleAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "an error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBarEntertainment.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBarEntertainment.visibility = View.VISIBLE
    }

    /** initialises recycler view containing list of articles*/
    private fun setUpRecyclerView() {
        articleAdapter = ArticleAdapter()
        rvEntertainmentNews.apply {
            adapter = articleAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}

