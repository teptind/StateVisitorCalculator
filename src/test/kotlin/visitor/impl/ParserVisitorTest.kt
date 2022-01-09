package visitor.impl

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.Token
import calculator.tokenizer.token.braces.*
import calculator.tokenizer.token.operations.*

import calculator.visitor.impl.ParserVisitor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import useVisitor

internal class ParserVisitorTest {
    companion object {
        @JvmStatic
        fun expressionsWithResults(): List<Arguments> = listOf(
            Arguments.of(
                listOf(Number(1), Add, Number(2)),
                listOf(Number(1), Number(2), Add),
            ),
            Arguments.of(
                listOf(OpeningBrace, Number(13), Mul, Number(2), ClosingBrace, Sub, Number(12)),
                listOf(Number(13), Number(2), Mul, Number(12), Sub),
            ),
            Arguments.of(
                listOf(Number(5), Div, OpeningBrace, Number(4), Add, Number(12), ClosingBrace),
                listOf(Number(5), Number(4), Number(12), Add, Div),
            ),
            Arguments.of(
                listOf(
                    OpeningBrace,
                    Number(1),
                    Add,
                    Number(6),
                    Mul,
                    Number(7),
                    ClosingBrace,
                    Sub,
                    OpeningBrace,
                    Number(2),
                    Mul,
                    Number(4),
                    ClosingBrace
                ),
                listOf(Number(1), Number(6), Number(7), Mul, Add, Number(2), Number(4), Mul, Sub),
            )
        )
    }

    private lateinit var visitor: ParserVisitor

    @BeforeEach
    fun init() {
        visitor = ParserVisitor()
    }

    @MethodSource("expressionsWithResults")
    @ParameterizedTest
    fun `should transform correctly`(expression: List<Token>, expectedResult: List<Token>) {
        Assertions.assertEquals(expectedResult, useVisitor(visitor, expression))
    }

}