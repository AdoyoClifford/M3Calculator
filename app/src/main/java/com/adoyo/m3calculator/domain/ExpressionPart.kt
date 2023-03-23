package com.adoyo.m3calculator.domain

sealed interface ExpressionPart {
    data class Number(val number: Int): ExpressionPart
    data class Operation(val operations: Operations): ExpressionPart
    data class Parenthesis(val type: ParenthesisType): ExpressionPart
}

sealed class ParenthesisType {
    object Opening: ParenthesisType()
    object Closing: ParenthesisType()
}