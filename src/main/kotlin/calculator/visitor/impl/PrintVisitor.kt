package calculator.visitor.impl

import calculator.tokenizer.token.Number
import calculator.tokenizer.token.braces.ClosingBrace
import calculator.tokenizer.token.braces.OpeningBrace
import calculator.tokenizer.token.operations.Add
import calculator.tokenizer.token.operations.Div
import calculator.tokenizer.token.operations.Mul
import calculator.tokenizer.token.operations.Sub
import calculator.visitor.TokenVisitor

class PrintVisitor : TokenVisitor<String> {
    private var result = arrayListOf<String>()

    override fun visit(token: Number) {
        result.add("NUMBER(${token.number})")
    }

    override fun visit(token: Add) {
        result.add("PLUS")
    }

    override fun visit(token: Sub) {
        result.add("MINUS")
    }

    override fun visit(token: Mul) {
        result.add("MUL")
    }

    override fun visit(token: Div) {
        result.add("DIV")
    }

    override fun visit(token: OpeningBrace) {
        result.add("LEFT")
    }

    override fun visit(token: ClosingBrace) {
        result.add("RIGHT")
    }

    override fun getResult(): String {
        return result.joinToString(" ")
    }
}