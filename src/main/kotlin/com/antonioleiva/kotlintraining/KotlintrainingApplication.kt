package com.antonioleiva.kotlintraining

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class KotlintrainingApplication

fun main(args: Array<String>) {
    runApplication2<KotlintrainingApplication>(*args)
}

inline fun <reified T> runApplication2(vararg args: String) {
    SpringApplication.run(T::class.java, *args)
}