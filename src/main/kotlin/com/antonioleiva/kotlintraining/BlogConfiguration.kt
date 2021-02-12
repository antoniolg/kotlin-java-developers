package com.antonioleiva.kotlintraining

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun provideArticleRepository() = ArticlesRepository()
}