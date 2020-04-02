package parser;

import exceptions.ParsingException;
import expressions.*;
import expressions.operations.*;

import java.util.List;

public class ExpressionParser extends BaseParser {
    private static final List<String> OPERATIONS = List.of("+", "-", "*", ">", "<", "=", "&", "|");

    public Expression parse(String text) {
        setSource(new StringSource(text));
        Expression result = parse();
        if (hasNext()) {
            throw new ParsingException();
        }
        return result;
    }

    private Expression parse() {
        if (test('(')) {
            Expression<?> left = parse();
            for (String operation : OPERATIONS) {
                if (test(operation)) {
                    Expression<?> right = parse();
                    expect(')');
                    return getExpression(operation, left, right);
                }
            }
        } else if (test("element")) {
            return new Element();
        } else {
            return getConst();
        }
        throw new ParsingException();
    }

    private ConstantExpression getConst() {
        StringBuilder builder = new StringBuilder();
        if (test('-')) {
            builder.append("-");
        }
        while (isDigit()) {
            builder.append(ch);
            nextChar();
        }
        try {
            return new ConstantExpression(builder.toString());
        } catch (NumberFormatException e) {
            throw new ParsingException();
        }
    }

    private Expression getExpression(String operation, Expression left, Expression right) {
        switch (operation) {
            case "+":
                return new Add(left, right);
            case "-":
                return new Subtract(left, right);
            case "*":
                return new Multiply(left, right);
            case ">":
                return new Greater(left, right);
            case "<":
                return new Less(left, right);
            case "=":
                return new Equal(left, right);
            case "&":
                return new And(left, right);
            case "|":
                return new Or(left, right);
            default:
                throw new ParsingException();
        }
    }
}
