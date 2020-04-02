package expressions.operations;

import expressions.ConstantExpression;
import expressions.Expression;
import expressions.ExpressionsHelper;

public final class Add extends BinaryExpression<Integer, Integer> {
    public Add(Expression<?> left, Expression<?> right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public Integer calculate(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Expression<Integer> simplify(Expression<Integer> left, Expression<Integer> right) {
        if (left instanceof ConstantExpression && right instanceof ConstantExpression) {
            return new ConstantExpression(left.evaluate(0) + right.evaluate(0));
        }
        if (ExpressionsHelper.isZero(left)) {
            return right;
        }
        if (ExpressionsHelper.isZero(right)) {
            return left;
        }
        return new Add(left, right);
    }

    @Override
    public Class<Integer> getKeyClass() {
        return Integer.class;
    }

    @Override
    public Class<Integer> getValueClass() {
        return Integer.class;
    }
}
