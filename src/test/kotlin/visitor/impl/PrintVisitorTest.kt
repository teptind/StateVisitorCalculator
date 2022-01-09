package visitor.impl

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.Token
import calculator.tokenizer.token.braces.ClosingBrace
import calculator.tokenizer.token.braces.OpeningBrace
import calculator.tokenizer.token.operations.*
import calculator.visitor.impl.PrintVisitor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import useVisitor

internal class PrintVisitorTest {

    companion object {
        @JvmStatic
        fun expressionsWithResults(): List<Arguments> = listOf(
            Arguments.of(
                listOf(Number(1), Number(2), Add),
                "NUMBER(1) NUMBER(2) PLUS"
            ),
            Arguments.of(
                listOf(Number(1), Number(6), Number(7), Mul, Add, Number(2), Number(4), Mul, Sub),
                "NUMBER(1) NUMBER(6) NUMBER(7) MUL PLUS NUMBER(2) NUMBER(4) MUL MINUS"
            ),
            Arguments.of(
                listOf(OpeningBrace, Number(30), Add, Number(2), ClosingBrace, Div, Number(8)),
                "LEFT NUMBER(30) PLUS NUMBER(2) RIGHT DIV NUMBER(8)"
            )
        )
    }

    private lateinit var visitor: PrintVisitor

    @BeforeEach
    fun init() {
        visitor = PrintVisitor()
    }

    @MethodSource("expressionsWithResults")
    @ParameterizedTest
    fun `should print correctly`(expression: List<Token>, expectedResult: String) {
        assertEquals(expectedResult, useVisitor(visitor, expression))
    }
}