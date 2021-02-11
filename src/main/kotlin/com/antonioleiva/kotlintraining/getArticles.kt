package com.antonioleiva.kotlintraining

fun getArticles(): List<Article> = listOf(
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

fun findArticleBySlug(slug: String): Article? {
    for (article in getArticles()) {
        if (article.slug == slug) {
            return article
        }
    }

    return null
}