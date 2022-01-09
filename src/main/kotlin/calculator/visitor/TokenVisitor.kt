package calculator.visitor

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.operations.*
import calculator.tokenizer.token.braces.*

interface TokenVisitor<T> {
    fun visit(token: Number)
    fun visit(token: Add)
    fun visit(token: Sub)
    fun visit(token: Mul)
    fun visit(token: Div)
    fun visit(token: OpeningBrace)
    fun visit(token: ClosingBrace)

    fun getResult(): T
}