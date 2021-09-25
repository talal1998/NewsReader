package com.example.newsreader.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsreader.R
import com.example.newsreader.db.ArticleDatabase
import com.example.newsreader.repository.NewsRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_news.*

/**
 * Class that creates the main Newsfeed Activity that displays the articles and toolbars
 * @author 956013
 */

class NewsActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        setUpNewsRepository()
        setUpNavController()
        setUpDrawerAndToolbar()
    }

    /** creates a viewmodel for the articles pulled from the api */
    private fun setUpNewsRepository(){
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
    }

    /** assigns the created nav graph to the nav drawer and bottom nav bar */
    private fun setUpNavController(){
        bottomNavigationView.setupWithNavController(navigationFragment.findNavController())
        navDrawerView.setupWithNavController(navigationFragment.findNavController())
    }

    /** assigns the toolbar as the main action bar and creates a drawer object */
    private fun setUpDrawerAndToolbar(){
        val mToolbar = findViewById<Toolbar>(R.id.app_bar)
        setSupportActionBar(mToolbar)
        drawerLayout = findViewById(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    /** handles the animations for the show navigation drawer icon on the toolbar*/
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /** uses firebase authentication to log the user out*/
    fun logOut(item: MenuItem) {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
