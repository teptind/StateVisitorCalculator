package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

data class Number(val number: Int): Token {
    override fun <T> accept(tokenVisitor: TokenVisitor<T>) {
        tokenVisitor.visit(this)
    }
}