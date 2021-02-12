package com.antonioleiva.kotlintraining

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.support.beans

@SpringBootApplication
class KotlintrainingApplication

fun main(args: Array<String>) {
    runApplication<KotlintrainingApplication>(*args) {
        addInitializers(beans)
    }
}

private val beans = beans {
    bean<LocalDatasource>()
    bean<RemoteDataSource>()
    bean<ArticlesRepository>()
}