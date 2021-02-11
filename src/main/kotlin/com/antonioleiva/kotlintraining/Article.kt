package com.antonioleiva.kotlintraining

data class Article(
    val title: String,
    val content: String,
    val slug: String = title.toSlug(),
    val type: Type
) {
    enum class Type {
        TEXT,
        VIDEO
    }
}