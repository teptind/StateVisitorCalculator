package calculator.tokenizer.token.operations

import calculator.visitor.TokenVisitor

object Sub : OperationToken {
    override fun <T> accept(tokenVisitor: TokenVisitor<T>) {
        tokenVisitor.visit(this)
    }
}