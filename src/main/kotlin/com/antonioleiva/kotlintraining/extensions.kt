package com.antonioleiva.kotlintraining

import org.springframework.ui.Model

fun String.toSlug() = this.toLowerCase().replace(' ', '-')

operator fun Model.set(attributeName: String, attributeValue: Any?) {
    addAttribute(attributeName, attributeValue)
}

fun Article.render(): RenderedArticle {
    val renderedTitle = when (type) {
        Article.Type.TEXT -> title
        Article.Type.VIDEO -> "$title (Video)"
    }

    return RenderedArticle(renderedTitle, content, slug)
}