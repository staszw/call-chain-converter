package expressions.operations;

import expressions.ConstantExpression;
import expressions.Expression;
import expressions.ExpressionsHelper;

public final class Subtract extends BinaryExpression<Integer, Integer> {
    public Subtract(Expression<?> left, Expression<?> right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public Integer calculate(Integer x, Integer y) {
        return x - y;
    }

    @Override
    public Expression<Integer> simplify(Expression<Integer> left, Expression<Integer> right) {
        if (ExpressionsHelper.isZero(right)){
            return left;
        }
        if (ExpressionsHelper.isZero(left)) {
            return new Multiply(new ConstantExpression(-1), right).simplify();
        }
        if (left.equals(right)) {
            return new ConstantExpression(0);
        }
        return new Subtract(left, right);
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
