package com.antonioleiva.kotlintraining

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlintrainingApplication

fun main(args: Array<String>) {
    runApplication<KotlintrainingApplication>(*args)

    printMessages(*args)
}

private fun printMessage(message: String) {
    println(message)
}

private fun printMessages(vararg messages: String) {
    for (m in messages) {
        println(m)
    }
}