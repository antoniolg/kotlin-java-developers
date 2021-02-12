package com.antonioleiva.kotlintraining

import org.springframework.ui.Model

fun String.toSlug() = this.toLowerCase().replace(' ', '-')

operator fun Model.set(attributeName: String, attributeValue: Any?) {
    addAttribute(attributeName, attributeValue)
}

typealias TitleRenderer = (Article) -> String

fun Article.render(titleRender: TitleRenderer = { it.title }): RenderedArticle {
    val popularInfo = if (popular) "(POPULAR)" else ""
    val likesInfo = "$likes likes $popularInfo"

    return RenderedArticle(titleRender(this), content, slug, likesInfo)
}

fun String?.toFilter(): Filter {
    return this
        ?.let { filter -> Article.Type.values().find { it.name.equals(filter, true) } }
        ?.let { type -> Filter.ByType(type) }
        ?: Filter.None
}

fun String.toRenderedFilter(selectedValue: String?): RenderedFilter =
    RenderedFilter(this, equals(selectedValue, true))