package tests;

import expressions.ConstantExpression;
import expressions.Element;
import expressions.Expression;
import expressions.operations.*;
import parser.ExpressionParser;

import java.util.List;
import java.util.Random;

public class ParserTest {
    private static final ExpressionParser parser = new ExpressionParser();
    private static final List<String> OPERATIONS = List.of("+", "-", "*", ">", "<", "=", "&", "|");
    private static final Random RANDOM = new Random();
    private static final int TEST_COUNT = 10000;

    public static void main(String[] args) {
        for (int i = 0; i < TEST_COUNT; i++) {
            Expression expected = generateTest();
            try {
                Expression found = parser.parse(expected.toString());
                if (!found.equals(expected)) {
                    System.out.println("Test failed. Input: " + expected + ".");
                    System.out.println("Expected: " + expected + ".");
                    System.out.println("Found: " + found + ".");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Unexpected error. Input: " + expected + ".");
                return;
            }
        }
        System.out.println("Total number of tests: " + TEST_COUNT + ". Passed tests: " + TEST_COUNT + ".");
    }

    public static Expression generateTest() {
        Expression expected = null;
        while (expected == null) {
            try {
                expected = generateExpression();
            } catch (Exception ignored) { }
        }
        return expected;
    }

    private static Expression generateExpression() {
        int type = RANDOM.nextInt(OPERATIONS.size() + 2);
        if (type == 0) {
            return new ConstantExpression(RANDOM.nextInt());
        } else if (type == 1) {
            return new Element();
        } else {
            Expression left = generateExpression();
            Expression right = generateExpression();
            switch (OPERATIONS.get(type - 2)) {
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
            }
            throw new AssertionError("Unsupported operation");
        }
    }
}
