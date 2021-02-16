package com.graduationproject.newsapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.graduationproject.newsapp.repository.NewsRepository

class NewsViewModelProvider(
    val newsRepository: NewsRepository
    , val app: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app,newsRepository) as T
    }
}