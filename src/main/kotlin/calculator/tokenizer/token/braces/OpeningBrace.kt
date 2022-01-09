package calculator.tokenizer.token.braces

import calculator.visitor.TokenVisitor

object OpeningBrace : BraceToken {
    override fun <T> accept(tokenVisitor: TokenVisitor<T>) {
        tokenVisitor.visit(this)
    }
}