package com.graduationproject.newsapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.graduationproject.newsapp.models.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article):Long

    @Query("select * from articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}