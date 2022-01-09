package calculator.tokenizer.token.operations

import calculator.visitor.TokenVisitor

object Mul : OperationToken {
    override fun <T> accept(tokenVisitor: TokenVisitor<T>) {
        tokenVisitor.visit(this)
    }
}