package com.antonioleiva.kotlintraining

import org.springframework.ui.Model

fun String.toSlug() = this.toLowerCase().replace(' ', '-')

operator fun Model.set(attributeName: String, attributeValue: Any?) {
    addAttribute(attributeName, attributeValue)
}

fun Article.render(titleRender: (Article) -> String = { it.title }): RenderedArticle {
    val popularInfo = if (popular) "(POPULAR)" else ""
    val likesInfo = "$likes likes $popularInfo"

    return RenderedArticle(titleRender(this), content, slug, likesInfo)
}