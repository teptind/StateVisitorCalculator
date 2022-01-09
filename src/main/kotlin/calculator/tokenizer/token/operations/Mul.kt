package calculator.tokenizer.token.operations

import calculator.visitor.TokenVisitor

object Mul : Operation {
    override fun <T> accept(tokenVisitor: TokenVisitor<T>) {
        tokenVisitor.visit(this)
    }
}