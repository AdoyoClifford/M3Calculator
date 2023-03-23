package com.adoyo.m3calculator.domain

class ExpressionEvaluator(private val expression: List<ExpressionPart>) {

    fun evaluate(): Double {
        return evalExpression(expression).value
    }

    private fun evalExpression(expression: List<ExpressionPart>): ExpressionResult {
        val result = evalTerm(expression)
        var remaining = result.remainingExpression
        var sum = result.value

        while (true) {
            when (remaining.firstOrNull()) {
                ExpressionPart.Operation(Operations.ADD) -> {
                    val term = evalTerm(remaining.drop(1))
                    sum += term.value
                    remaining = term.remainingExpression
                }

                ExpressionPart.Operation(Operations.SUBTRACT) -> {
                    val term = evalTerm(remaining.drop(1))
                    sum -= term.value
                    remaining = term.remainingExpression
                }

                else -> return ExpressionResult(remaining, sum)
            }
        }
    }

    private fun evalTerm(expression: List<ExpressionPart>): ExpressionResult {
        val result = evalFactor(expression)
        var remaining = result.remainingExpression
        var sum = result.value
        while (true) {
            when (remaining.firstOrNull()) {
                ExpressionPart.Operation(Operations.MULTIPLY) -> {
                    val factor = evalFactor(remaining.drop(1))
                    sum *= factor.value
                    remaining = factor.remainingExpression
                }

                ExpressionPart.Operation(Operations.DIVIDE) -> {
                    val factor = evalFactor(remaining.drop(1))
                    sum /= factor.value
                    remaining = factor.remainingExpression
                }

                ExpressionPart.Operation(Operations.PERCENT) -> {
                    val factor = evalFactor(remaining.drop(1))
                    sum *= (factor.value / 100)
                    remaining = factor.remainingExpression
                }

                else -> return ExpressionResult(remaining, sum)
            }
        }
    }

    private fun evalFactor(expression: List<ExpressionPart>): ExpressionResult {
        return when (val part = expression.firstOrNull()) {
            ExpressionPart.Operation(Operations.ADD) -> {
                evalFactor(expression.drop(1))
            }

            ExpressionPart.Operation(Operations.SUBTRACT) -> {
                evalFactor(expression.drop(1)).run {
                    ExpressionResult(remainingExpression, -value)
                }
            }

            ExpressionPart.Parenthesis(ParenthesisType.Opening) -> {
                evalFactor(expression.drop(1)).run {
                    ExpressionResult(remainingExpression, value)
                }
            }

            is ExpressionPart.Parenthesis -> evalTerm(expression.drop(1))
            is ExpressionPart.Number -> ExpressionResult(
                remainingExpression = expression.drop(1),
                value = part.number
            )

            else -> throw IllegalArgumentException("Invalid Part")
        }
    }

    data class ExpressionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double
    )
}