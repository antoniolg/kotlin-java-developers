package com.antonioleiva.kotlintraining

import com.antonioleiva.kotlintraining.Article.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class HtmlController(val repository: ArticlesRepository) {

    companion object {
        const val BLOG = "blog"
        const val ARTICLE = "article"
        private val STRING_FILTERS = listOf("ALL") + Type.values().map(Type::toString)
    }

    @GetMapping("/")
    suspend fun blog(@RequestParam(value = "filter", required = false) selectedFilter: String?, model: Model): String =
        coroutineScope {
            model["title"] = "Blog"
            model["filters"] = STRING_FILTERS.map { it.toRenderedFilter(selectedFilter) }

            val a1 = async(Dispatchers.IO) { repository.getByFilter(selectedFilter.toFilter()).map { it.render() } }
            val a2 = async(Dispatchers.IO) { repository.getByFilter(selectedFilter.toFilter()).map { it.render() } }

            model["articles"] = a1.await() + a2.await()
            BLOG
        }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        requireNotNull(repository.findArticleBySlug(slug)).let { article ->
            val renderedArticle = article.render(::renderTitle)
            model["title"] = article.title
            model["article"] = renderedArticle
        }

        return ARTICLE
    }

    @GetMapping("/article/{slug}/like")
    fun likeArticle(@PathVariable slug: String, model: Model): String {
        requireNotNull(repository.findArticleBySlug(slug)).let {
            it.likes++
            return article(slug, model)
        }
    }

    @PostMapping("/filter")
    fun filter(@ModelAttribute filter: RenderedFilter): String {
        return "redirect:/?filter=${filter.value}"
    }

    private fun renderTitle(article: Article) = when (article.type) {
        Type.TEXT -> article.title
        Type.VIDEO -> "${article.title} (Video)"
    }

}