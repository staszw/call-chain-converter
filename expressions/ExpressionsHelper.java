package expressions;

public final class ExpressionsHelper {
    public static final Expression ZERO = new ConstantExpression(0);
    public static final Expression ONE = new ConstantExpression(1);
    public static final Expression ELEMENT = new Element();
    public static boolean isZero(Expression expression) {
        return expression.equals(ZERO);
    }

    public static boolean isOne(Expression expression) {
        return expression.equals(ONE);
    }

    public static boolean isElement(Expression expression) {
        return expression.equals(ELEMENT);
    }
}
