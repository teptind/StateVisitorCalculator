package calculator.tokenizer.impl.state

import calculator.tokenizer.impl.TokenizerImpl
import calculator.tokenizer.token.Number

class AccumulatingNumberState(tokenizer: TokenizerImpl, firstDigit: Char): TokenizerState(tokenizer) {
    private var curNumber = firstDigit.toNumber()

    override fun onConsume(char: Char) {
        if (char in '0'..'9') {
            curNumber = curNumber * 10 + char.toNumber()
        } else {
            InitialState(tokenizer).also {
                tokenizer.changeState(it)
                it.onConsume(char)
            }

        }
    }

    override fun onExit() {
        tokenizer.addToken(Number(curNumber))
    }

    private fun Char.toNumber() = this - '0'
}