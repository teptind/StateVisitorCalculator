import calculator.tokenizer.impl.TokenizerImpl
import calculator.tokenizer.token.Token
import calculator.visitor.TokenVisitor
import calculator.visitor.impl.CalcVisitor
import calculator.visitor.impl.ParserVisitor
import calculator.visitor.impl.PrintVisitor

// (23 + 10) * 5 - 3 * (32 + 5) * (10 - 4 * 5) + 8 / 2
// 1279

fun main() {
    val polandTokens = reformatToPoland(getTokens(readExpression()))

    printExpression(polandTokens)

    val result = calculate(polandTokens)

    println("Result: $result")
}

private fun readExpression(): String {
    val expression = readLine()
    require(expression != null && expression.isNotEmpty()) { "Expression can't be null or empty" }

    return expression
}

private fun getTokens(expr: String) = TokenizerImpl().getTokens(expr)

private fun printExpression(tokens: List<Token>) = println(useVisitor(PrintVisitor(), tokens))

private fun reformatToPoland(tokens: List<Token>): List<Token> = useVisitor(ParserVisitor(), tokens)

private fun calculate(polandTokens: List<Token>): Int = useVisitor(CalcVisitor(), polandTokens)

fun <T> useVisitor(visitor: TokenVisitor<T>, tokens: List<Token>): T {
    tokens.forEach { it.accept(visitor) }

    return visitor.getResult()
}
