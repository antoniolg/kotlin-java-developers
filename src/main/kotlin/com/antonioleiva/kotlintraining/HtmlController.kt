package com.antonioleiva.kotlintraining

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HtmlController {

    companion object {
        const val BLOG = "blog"
        const val ARTICLE = "article"
    }

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = "Blog"
        model["articles"] = ArticlesRepository.getArticles().map { it.render() }
        return BLOG
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        requireNotNull(ArticlesRepository.findArticleBySlug(slug)).let { article ->
            val renderedArticle = article.render(::renderTitle)
            model["title"] = article.title
            model["article"] = renderedArticle
        }

        return ARTICLE
    }

    @GetMapping("/article/{slug}/like")
    fun likeArticle(@PathVariable slug: String, model: Model): String {
        requireNotNull(ArticlesRepository.findArticleBySlug(slug)).let {
            it.likes++
            return article(slug, model)
        }
    }

    private fun renderTitle(article: Article) = when (article.type) {
        Article.Type.TEXT -> article.title
        Article.Type.VIDEO -> "${article.title} (Video)"
    }

}