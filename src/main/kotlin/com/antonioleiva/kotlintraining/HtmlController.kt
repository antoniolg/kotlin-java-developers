package com.antonioleiva.kotlintraining

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HtmlController {

    private val repository by lazy { ArticlesRepository() }

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = convertArticles(repository.getArticles())
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        requireNotNull(repository.findArticleBySlug(slug)).let { article ->
            val renderedArticle = article.render(::renderTitle)
            model["title"] = article.title
            model["article"] = renderedArticle
        }

        return "article"
    }

    private fun renderTitle(article: Article) = when (article.type) {
        Article.Type.TEXT -> article.title
        Article.Type.VIDEO -> "${article.title} (Video)"
    }

    private fun convertArticles(articles: List<Article>): List<RenderedArticle> {
        val result = ArrayList<RenderedArticle>()

        for (article in articles) {
            result.add(article.render())
        }

        return result
    }

}