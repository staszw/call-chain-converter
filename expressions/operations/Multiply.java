package expressions.operations;

import expressions.ConstantExpression;
import expressions.Expression;
import expressions.ExpressionsHelper;

public final class Multiply extends BinaryExpression<Integer, Integer> {

    public Multiply(Expression<?> left, Expression<?> right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public Integer calculate(Integer x, Integer y) {
        return x * y;
    }

    @Override
    public Expression<Integer> simplify(Expression<Integer> left, Expression<Integer> right) {
        if (ExpressionsHelper.isOne(left)) {
            return right;
        }
        if (ExpressionsHelper.isOne(right)) {
            return left;
        }
        if (ExpressionsHelper.isZero(left) || ExpressionsHelper.isZero(right)) {
            return new ConstantExpression(0);
        }
        return new Multiply(left, right);
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
