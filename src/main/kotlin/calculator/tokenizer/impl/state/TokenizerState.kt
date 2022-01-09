package calculator.tokenizer.impl.state

import calculator.tokenizer.impl.TokenizerImpl

abstract class TokenizerState(protected val tokenizer: TokenizerImpl) {
    open fun onEnter() {}

    abstract fun onConsume(char: Char)

    open fun onExit() {}
}