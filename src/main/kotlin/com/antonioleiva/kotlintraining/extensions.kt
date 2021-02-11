package com.antonioleiva.kotlintraining

fun String.toSlug() = this.toLowerCase().replace(' ', '-')