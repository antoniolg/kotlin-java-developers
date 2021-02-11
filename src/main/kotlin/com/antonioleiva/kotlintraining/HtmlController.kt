package com.antonioleiva.kotlintraining

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HtmlController {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = convertArticles(getArticles())
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        requireNotNull(findArticleBySlug(slug)).let {
            val renderedArticle = it.render(object : TitleRender {
                override fun renderTitle(article: Article): String = when (article.type) {
                    Article.Type.TEXT -> article.title
                    Article.Type.VIDEO -> "${article.title} (Video)"
                }
            })
            model["title"] = it.title
            model["article"] = renderedArticle
        }

        return "article"
    }

    private fun convertArticles(articles: List<Article>): List<RenderedArticle> {
        val result = ArrayList<RenderedArticle>()

        for (article in articles) {
            result.add(article.render(object : TitleRender {
                override fun renderTitle(article: Article): String = article.title
            }))
        }

        return result
    }

}