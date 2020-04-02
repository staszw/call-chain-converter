package expressions.operations;

import expressions.BooleanConstant;
import expressions.ConstantExpression;
import expressions.Expression;

public final class Greater extends BinaryExpression<Integer, Boolean> {
    public Greater(Expression<?> left, Expression<?> right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return ">";
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public Boolean calculate(Integer x, Integer y) {
        return x > y;
    }

    @Override
    public Expression<Boolean> simplify(Expression<Integer> left, Expression<Integer> right) {
        Expression<Integer> newLeft = new Subtract(left, right).simplify();
        if (newLeft instanceof ConstantExpression) {
            return new BooleanConstant(((ConstantExpression) newLeft).getValue() > 0);
        }
        return new Greater(left, right);
    }

    @Override
    public Class<Integer> getKeyClass() {
        return Integer.class;
    }

    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }
}
