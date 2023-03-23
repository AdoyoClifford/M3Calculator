package com.adoyo.m3calculator.domain

sealed interface CalculatorActions {
    object Clear : CalculatorActions
    object Delete : CalculatorActions
    object Calculate : CalculatorActions
    object Decimal : CalculatorActions
    object Parenthesis : CalculatorActions
    data class Number(val number: Int) : CalculatorActions
    data class Operation(val operations: Operations) : CalculatorActions
}