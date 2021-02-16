package com.graduationproject.newsapp.models

import com.graduationproject.newsapp.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)