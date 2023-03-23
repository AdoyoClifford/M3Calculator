package com.adoyo.m3calculator.domain

enum class Operations(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('+'),
    PERCENT('%')
}

val operationSymbol = Operations.values().map { it.symbol }.joinToString { "" }

fun operationFromSymbol(symbol: Char): Operations {
    return Operations.values().find { it.symbol == symbol }
        ?: throw IllegalArgumentException("Invalid Symbol")
}