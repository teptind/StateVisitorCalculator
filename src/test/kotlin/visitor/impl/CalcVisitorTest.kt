package visitor.impl

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.Token
import calculator.tokenizer.token.operations.*
import calculator.visitor.impl.CalcVisitor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import useVisitor


internal class CalcVisitorTest {
    companion object {
        @JvmStatic
        fun expressionsWithResults(): List<Arguments> = listOf(
            Arguments.of(
                listOf(Number(1), Number(2), Add),
                3
            ),
            Arguments.of(
                listOf(Number(13), Number(2), Mul, Number(12), Sub),
                14
            ),
            Arguments.of(
                listOf(Number(5), Number(4), Number(12), Add, Div),
                0
            ),
            Arguments.of(
                listOf(Number(1), Number(6), Number(7), Mul, Add, Number(2), Number(4), Mul, Sub),
                35
            )
        )
    }

    private lateinit var visitor: CalcVisitor

    @BeforeEach
    fun init() {
        visitor = CalcVisitor()
    }

    @MethodSource("expressionsWithResults")
    @ParameterizedTest
    fun `should calculate correctly`(expression: List<Token>, expectedResult: Int) {
        assertEquals(expectedResult, useVisitor(visitor, expression))
    }
}