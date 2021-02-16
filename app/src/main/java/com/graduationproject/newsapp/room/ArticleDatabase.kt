package com.graduationproject.newsapp.room

import android.content.Context
import androidx.room.*
import com.graduationproject.newsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile
        private var inctance: ArticleDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = inctance ?: synchronized(LOCK) {
            inctance?:createDatabase(context).also{ inctance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "articles_db.db"
            ).build()

    }
}