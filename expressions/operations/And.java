package expressions.operations;

import expressions.BooleanConstant;
import expressions.Expression;

public final class And extends BinaryExpression<Boolean, Boolean> {
    public And(Expression<?> left, Expression<?> right) {
        super(left, right);
    }

    @Override
    public String getSymbol() {
        return "&";
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public Boolean calculate(Boolean x, Boolean y) {
        return x & y;
    }

    @Override
    public Expression<Boolean> simplify(Expression<Boolean> left, Expression<Boolean> right) {
        if (left instanceof BooleanConstant) {
            if (((BooleanConstant) left).getValue() == false) {
                return left;
            } else {
                return right;
            }
        }
        if (right instanceof BooleanConstant) {
            if (((BooleanConstant) right).getValue() == false) {
                return right;
            } else {
                return left;
            }
        }
        return new And(left, right);
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
