package calculator.tokenizer.token.braces

import calculator.visitor.TokenVisitor

object ClosingBrace : Brace {
    override fun <T> accept(tokenVisitor: TokenVisitor<T>) {
        tokenVisitor.visit(this)
    }
}