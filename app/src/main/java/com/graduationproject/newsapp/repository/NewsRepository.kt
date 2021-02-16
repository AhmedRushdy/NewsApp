package com.graduationproject.newsapp.repository

import com.graduationproject.newsapp.api.RetrofitInctance
import com.graduationproject.newsapp.models.Article
import com.graduationproject.newsapp.room.ArticleDatabase

class NewsRepository(
    private val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInctance.api.getBreakingNews()

    suspend fun searchNews(searchFor:String,pageNumber: Int)
        = RetrofitInctance.api.searchForNews(searchFor,pageNumber)

    suspend fun insert(article:Article)= db.getArticleDao().insert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) =  db.getArticleDao().deleteArticle(article)
}