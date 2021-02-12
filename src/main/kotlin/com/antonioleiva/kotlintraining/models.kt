package com.antonioleiva.kotlintraining

import kotlin.properties.Delegates

data class Article(
    val title: String,
    val content: String,
    val slug: String = title.toSlug(),
    val type: Type,
    var popular: Boolean = false
) {
    var likes by Delegates.observable(0) { _, _, new ->
        popular = new >= 3
    }

    enum class Type {
        TEXT,
        VIDEO
    }
}

data class RenderedArticle(
    val title: String,
    val content: String,
    val slug: String,
    val likesInfo: String
)

sealed class Filter {
    object None : Filter()
    class ByType(val type: Article.Type) : Filter()
}

data class RenderedFilter(val value: String, val selected: Boolean = false)