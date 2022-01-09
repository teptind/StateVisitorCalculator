package calculator.tokenizer.impl.state

import calculator.tokenizer.impl.TokenizerImpl
import calculator.tokenizer.token.braces.*
import calculator.tokenizer.token.operations.*

class InitialState(tokenizer: TokenizerImpl): TokenizerState(tokenizer) {
    override fun onConsume(char: Char) {
        if (char.isWhitespace()) return

        if (char.isDigit()) {
            tokenizer.changeState(AccumulatingNumberState(tokenizer, char))
            return
        }

        tokenizer.addToken(
            when (char) {
                '(' -> OpeningBrace
                ')' -> ClosingBrace
                '+' -> Add
                '-' -> Sub
                '*' -> Mul
                '/' -> Div

                else -> error("Unknown character: $char")
            }
        )
    }
}