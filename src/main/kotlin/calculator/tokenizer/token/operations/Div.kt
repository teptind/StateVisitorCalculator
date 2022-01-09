package calculator.tokenizer.token.operations

import calculator.visitor.TokenVisitor

object Div : OperationToken {
    override fun <T> accept(tokenVisitor: TokenVisitor<T>) {
        tokenVisitor.visit(this)
    }
}