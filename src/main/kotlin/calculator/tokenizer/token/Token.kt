package calculator.tokenizer.token

import calculator.visitor.TokenVisitor

interface Token {
    fun <T> accept(tokenVisitor: TokenVisitor<T>)
}