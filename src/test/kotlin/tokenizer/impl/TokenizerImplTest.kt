package tokenizer.impl

import calculator.tokenizer.impl.TokenizerImpl
import calculator.tokenizer.token.operations.*
import calculator.tokenizer.token.braces.*
import calculator.tokenizer.token.Number
import calculator.tokenizer.token.Token
import java.lang.IllegalStateException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class TokenizerImplTest {
    companion object {
        @JvmStatic
        fun expressionsWithResults(): List<Arguments> = listOf(
            Arguments.of(
                "1 + 2",
                listOf(Number(1), Add, Number(2))
            ),
            Arguments.of(
                "(13 * 2) - 12",
                listOf(OpeningBrace, Number(13), Mul, Number(2), ClosingBrace, Sub, Number(12))
            ),
            Arguments.of(
                "5 / (4 + 12))",
                listOf(Number(5), Div, OpeningBrace, Number(4), Add, Number(12), ClosingBrace, ClosingBrace)
            )
        )
    }

    private lateinit var tokenizer: TokenizerImpl

    @BeforeEach
    fun init() {
        tokenizer = TokenizerImpl()
    }

    @MethodSource("expressionsWithResults")
    @ParameterizedTest
    fun `should tokenize correctly`(expression: String, expectedResult: List<Token>) {
        assertEquals(expectedResult, tokenizer.getTokens(expression))
    }

    @Test
    fun `should throw when unknown character`() {
        val exception = assertThrows<IllegalStateException> { tokenizer.getTokens("(13 * 2) -! 12") }

        assertEquals("Unknown character: !", exception.message)
    }

}