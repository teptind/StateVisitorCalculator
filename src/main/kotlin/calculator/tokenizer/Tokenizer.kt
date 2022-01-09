package calculator.tokenizer

import calculator.tokenizer.token.Token

interface Tokenizer {
    fun getTokens(expression: String): List<Token>
}