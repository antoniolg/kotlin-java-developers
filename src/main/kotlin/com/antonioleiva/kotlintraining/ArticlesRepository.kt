package com.antonioleiva.kotlintraining

import com.antonioleiva.kotlintraining.Article.Type.TEXT
import com.antonioleiva.kotlintraining.Article.Type.VIDEO

class ArticlesRepository {

    private val articles = (1..10).map {
        Article(
            title = "Title $it",
            content = "Content ".repeat(it),
            type = if (it % 3 == 0) VIDEO else TEXT
        )
    }

    fun getArticles(): List<Article> = articles

    fun findArticleBySlug(slug: String): Article? = articles.find { it.slug == slug }

    fun getByFilter(filter: Filter): List<Article> {
        Thread.sleep(2000)
        return when (filter) {
            is Filter.ByType -> articles.filter { it.type == filter.type }
            Filter.None -> articles
        }
    }

}