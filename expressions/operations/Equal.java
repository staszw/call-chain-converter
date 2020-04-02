package expressions.operations;

import expressions.BooleanConstant;
import expressions.ConstantExpression;
import expressions.Expression;
import expressions.ExpressionsHelper;

public final class Equal extends BinaryExpression<Integer, Boolean> {
    public Equal(Expression<?> left, Expression<?> right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "=";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public Boolean calculate(Integer x, Integer y) {
        return x.equals(y);
    }

    @Override
    public Expression<Boolean> simplify(Expression<Integer> left, Expression<Integer> right) {
        Expression<Integer> newLeft = new Subtract(left, right).simplify();
        if (newLeft instanceof ConstantExpression) {
            return new BooleanConstant(((ConstantExpression) newLeft).getValue() == 0);
        }
        return new Equal(newLeft, ExpressionsHelper.ZERO);
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
