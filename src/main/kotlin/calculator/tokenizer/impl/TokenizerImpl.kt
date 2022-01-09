package calculator.tokenizer.impl

import calculator.tokenizer.Tokenizer
import calculator.tokenizer.impl.state.InitialState
import calculator.tokenizer.impl.state.TokenizerState
import calculator.tokenizer.token.Token

class TokenizerImpl : Tokenizer {
    private val tokens: MutableList<Token> = arrayListOf()

    private var state: TokenizerState = InitialState(this)

    override fun getTokens(expression: String): List<Token> {


        expression.forEach { this.state.onConsume(it) }
        state.onExit()

        return tokens
    }

    fun changeState(newState: TokenizerState) {
        state.onExit()
        state = newState
        state.onEnter()
    }

    fun addToken(token: Token) = tokens.add(token)

    private fun clear() {
        tokens.clear()
        state = InitialState(this)
    }
}