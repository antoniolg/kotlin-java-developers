package com.antonioleiva.kotlintraining

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @GetMapping("/")
    fun blog(model: Model): String {
        model.addAttribute("title", "Blog")
        model.addAttribute("articles", getArticles())
        return "blog"
    }

}