package com.antonioleiva.kotlintraining

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlintrainingApplication

fun main(args: Array<String>) {
    runApplication<KotlintrainingApplication>(*args)

    val name = args[0]
    "Hello $name".println()
}

private fun String.println() {
    println(this)
}