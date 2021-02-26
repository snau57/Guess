package com.rachen0101.guess

import java.util.*

fun main() {
    val secretNumber = SecretNumber()
    println(secretNumber.secret)
    println("${secretNumber.validate(2)}, count: ${secretNumber.count}")
}

class SecretNumber {
    val secret = Random().nextInt(10) + 1
    var count = 0

    fun validate(number: Int) : Int {
        count++
        return number - secret
    }
}