package expressions.operations;

import expressions.Expression;

public final class Or extends BinaryExpression<Boolean, Boolean> {
    public Or(Expression<?> left, Expression<?> right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "|";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public Boolean calculate(Boolean x, Boolean y) {
        return x | y;
    }

    @Override
    public Expression<Boolean> simplify(Expression<Boolean> left, Expression<Boolean> right) {
        return new Or(left, right);
    }

    @Override
    public Class<Boolean> getKeyClass() {
        return Boolean.class;
    }

    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }
}
