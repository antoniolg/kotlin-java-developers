package com.antonioleiva.kotlintraining

class ArticlesRepository {

    private val articles = listOf(
        Article(
            title = "Reactor Bismuth is out",
            content = "dolor sit amet",
            type = Article.Type.TEXT
        ),
        Article(
            title = "Reactor Aluminium has landed",
            content = "dolor sit amet",
            type = Article.Type.VIDEO
        )
    )

    fun getArticles(): List<Article> = articles

    fun findArticleBySlug(slug: String): Article? {
        for (article in articles) {
            if (article.slug == slug) {
                return article
            }
        }

        return null
    }
}