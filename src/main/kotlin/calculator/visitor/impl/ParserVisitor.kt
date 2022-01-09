package calculator.visitor.impl

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.Token
import calculator.tokenizer.token.braces.BraceToken
import calculator.tokenizer.token.braces.ClosingBrace
import calculator.tokenizer.token.braces.OpeningBrace
import calculator.tokenizer.token.operations.Add
import calculator.tokenizer.token.operations.Div
import calculator.tokenizer.token.operations.Mul
import calculator.tokenizer.token.operations.OperationToken
import calculator.tokenizer.token.operations.Sub
import calculator.visitor.TokenVisitor
import java.util.ArrayDeque
import java.util.Deque

class ParserVisitor : TokenVisitor<List<Token>> {
    companion object {
        @JvmStatic
        val operationRanks: Map<OperationToken, Int> = mapOf(
            Add to 1,
            Sub to 1,
            Mul to 2,
            Div to 2
        )
    }

    private val result = arrayListOf<Token>()
    private val stack: Deque<Token> = ArrayDeque()

    override fun visit(token: Number) {
        result.add(token)
    }

    override fun visit(token: Add) {
        addFromStack(token)
        stack.push(token)
    }

    override fun visit(token: Sub) {
        addFromStack(token)
        stack.push(token)
    }

    override fun visit(token: Mul) {
        addFromStack(token)
        stack.push(token)
    }

    override fun visit(token: Div) {
        addFromStack(token)
        stack.push(token)
    }

    override fun visit(token: OpeningBrace) {
        stack.push(token)
    }

    override fun visit(token: ClosingBrace) {
        while (stack.isNotEmpty() && stack.peek() !== OpeningBrace) {
            result.add(stack.pop())
        }

        if (stack.isEmpty()) error("Incorrect braces")

        stack.pop()
    }

    override fun getResult(): List<Token> {
        while (stack.isNotEmpty()) {
            val cur = stack.pop()

            if (cur !is BraceToken) {
                result.add(cur)
            }
        }

        return result.toList()
    }

    private fun addFromStack(operationToken: OperationToken) {
        while (stack.isNotEmpty() && stack.peek() is OperationToken
            && operationRanks[stack.peek()]!! >= operationRanks[operationToken]!!) {
            result.add(stack.pop())
        }
    }
}