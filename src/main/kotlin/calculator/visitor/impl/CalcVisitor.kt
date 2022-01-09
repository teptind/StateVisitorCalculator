package calculator.visitor.impl

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.braces.ClosingBrace
import calculator.tokenizer.token.braces.OpeningBrace
import calculator.tokenizer.token.operations.Add
import calculator.tokenizer.token.operations.Div
import calculator.tokenizer.token.operations.Mul
import calculator.tokenizer.token.operations.Sub
import calculator.visitor.TokenVisitor
import java.util.function.IntBinaryOperator


class CalcVisitor : TokenVisitor<Int> {
    private val stack = ArrayDeque<Int>()

    override fun visit(token: Number) {
        stack.add(token.number)
    }

    override fun visit(token: Add) {
        doOperation(Int::plus)
    }

    override fun visit(token: Sub) {
        doOperation(Int::minus)
    }

    override fun visit(token: Mul) {
        doOperation(Int::times)
    }

    override fun visit(token: Div) {
        doOperation(Int::div)
    }

    override fun visit(token: OpeningBrace) {
        error("There is no braces in reversed poland notation")
    }

    override fun visit(token: ClosingBrace) {
        error("There is no braces in reversed poland notation")
    }

    override fun getResult(): Int {
        return pop()
    }

    private fun pop(): Int {
        return stack.removeLastOrNull() ?: throw IllegalStateException("Incorrect poland notation")
    }

    private fun doOperation(operation: IntBinaryOperator) {
        val first = pop()
        val second = pop()

        stack.add(operation.applyAsInt(second, first))
    }
}