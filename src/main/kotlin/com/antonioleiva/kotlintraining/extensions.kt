package com.antonioleiva.kotlintraining

import org.springframework.ui.Model

fun String.toSlug() = this.toLowerCase().replace(' ', '-')

operator fun Model.set(attributeName: String, attributeValue: Any?) {
    addAttribute(attributeName, attributeValue)
}

fun Article.render(titleRender: (Article) -> String = { it.title }): RenderedArticle =
    RenderedArticle(titleRender(this), content, slug)