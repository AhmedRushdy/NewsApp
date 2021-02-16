package com.graduationproject.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.graduationproject.newsapp.R
import com.graduationproject.newsapp.databinding.ActivityNewsBinding
import com.graduationproject.newsapp.repository.NewsRepository
import com.graduationproject.newsapp.room.ArticleDatabase


class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private lateinit var navController: NavController
    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelProvider = NewsViewModelProvider(repository,application)
        viewModel = ViewModelProvider(this,viewModelProvider).get(NewsViewModel::class.java)


        //setting navBottom
        val navFragmentHost = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navFragmentHost.navController
        binding.navBottom.setupWithNavController(navController)

    }
}