package com.adoyo.m3calculator.domain

class ExpressionWriter {
    var expression = ""

    private fun canEnterOperation(operation: Operations): Boolean {
        if (operation in listOf(Operations.ADD, Operations.SUBTRACT)) {
            return expression.isEmpty() || expression.last() in "$operationSymbol()0123456789"
        }
        return expression.isNotEmpty() || expression.last() in "0123456789)"
    }

    private fun canEnterDecimal(operation: Operations): Boolean {
        if (expression.isEmpty() || expression.last() in "$operationSymbol.()") {
            return false
        }
        return !expression.takeLastWhile {
            it in "0123456789"
        }.contains(".")

    }

    private fun processParenthesis() {
        val openingCount = expression.count {it == '('}
        val closingCount = expression.count {it == ')'}
        expression += when {
            expression.isEmpty() || expression.last() in "$operationSymbol("->"("
                    expression.last() in "0123456789)" && openingCount == closingCount -> return
            else -> ")"
        }
    }
}