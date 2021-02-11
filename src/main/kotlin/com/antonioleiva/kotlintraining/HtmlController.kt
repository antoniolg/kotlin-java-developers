package com.antonioleiva.kotlintraining

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = convertArticles(getArticles())
        return "blog"
    }

    private fun convertArticles(articles: List<Article>): List<RenderedArticle> {
        val result = ArrayList<RenderedArticle>()

        for (article in articles) {

            val renderedTitle = when (article.type) {
                Article.Type.TEXT -> article.title
                Article.Type.VIDEO -> "${article.title} (Video)"
            }

            val renderedArticle = RenderedArticle(renderedTitle, article.content, article.slug)

            result.add(renderedArticle)
        }

        return result
    }

}