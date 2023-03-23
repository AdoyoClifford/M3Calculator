package com.adoyo.m3calculator.domain

class ExpressionWriter {
    var expression = ""

    fun processAction(action: CalculatorActions) {
        when (action) {
            CalculatorActions.Calculate -> {
                val parser = ExpressionParser(prepareCalculation())
                val evaluator = ExpressionEvaluator(parser.parse())
                expression = evaluator.evaluate().toString()
            }

            CalculatorActions.Clear -> {
                expression = ""
            }

            CalculatorActions.Decimal -> {
                if (canEnterDecimal()) {
                    expression += "."
                }
            }

            CalculatorActions.Delete -> {
                expression = expression.dropLast(1)
            }

            is CalculatorActions.Number -> {
                expression += action.number
            }

            is CalculatorActions.Operation -> {
                if (canEnterOperation(action.operations)) {
                    expression += action.operations.symbol
                }
            }

            CalculatorActions.Parenthesis -> {
                processParenthesis()
            }
        }
    }

    private fun canEnterOperation(operation: Operations): Boolean {
        if (operation in listOf(Operations.ADD, Operations.SUBTRACT)) {
            return expression.isEmpty() || expression.last() in "$operationSymbol()0123456789"
        }
        return expression.isNotEmpty() || expression.last() in "0123456789)"
    }

    private fun canEnterDecimal(): Boolean {
        if (expression.isEmpty() || expression.last() in "$operationSymbol.()") {
            return false
        }
        return !expression.takeLastWhile {
            it in "0123456789"
        }.contains(".")

    }

    private fun processParenthesis() {
        val openingCount = expression.count { it == '(' }
        val closingCount = expression.count { it == ')' }
        expression += when {
            expression.isEmpty() || expression.last() in "$operationSymbol(" -> "("
            expression.last() in "0123456789)" && openingCount == closingCount -> return
            else -> ")"
        }
    }

    private fun prepareCalculation(): String {
        val newExpression = expression.dropLastWhile {
            it in "$operationSymbol(."
        }
        if (newExpression.isEmpty()) {
            return "0"
        }
        return newExpression
    }
}