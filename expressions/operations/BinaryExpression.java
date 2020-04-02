package expressions.operations;

import exceptions.TypeMismatchException;
import expressions.Expression;
import expressions.ExpressionsHelper;

import java.util.Objects;

public abstract class BinaryExpression<K, V> implements Expression<V> {
    protected Expression<K> left;
    protected Expression<K> right;

    public BinaryExpression(Expression left, Expression right) {
        if (isReturningRightClass(left) && isReturningRightClass(right)) {
            this.left = (Expression<K>) left;
            this.right = (Expression<K>) right;
        } else {
            throw new TypeMismatchException();
        }
    }

    private boolean isReturningRightClass(Expression expression) {
        return getKeyClass().isAssignableFrom(expression.getValueClass());
    }

    public abstract String getSymbol();

    public abstract boolean isAssociative();

    public abstract V calculate(K x, K y);

    public abstract Expression<V> simplify(Expression<K> left, Expression<K> right);

    public abstract Class<K> getKeyClass();

    @Override
    public V evaluate(int x) {
        K leftValue = left.evaluate(x);
        K rightValue = right.evaluate(x);
        return calculate(leftValue, rightValue);
    }

    @Override
    public Expression<V> simplify() {
        return simplify(left.simplify(), right.simplify());
    }

    @Override
    public String toString() {
        return "(" + left + getSymbol() + right + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BinaryExpression) {
            BinaryExpression binaryExpression = (BinaryExpression) obj;
            return this.getClass() == binaryExpression.getClass()
                    && ((Objects.equals(left, binaryExpression.left)
                    && Objects.equals(right, binaryExpression.right))
                    || (binaryExpression.isAssociative()
                    && Objects.equals(left, binaryExpression.right)
                    && Objects.equals(right, binaryExpression.left)));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, getClass());
    }

    @Override
    public void replace(Expression<Integer> newElement) {
        if (ExpressionsHelper.isElement(left)) {
            left = (Expression<K>) newElement;
        } else {
            left.replace(newElement);
        }
        if (ExpressionsHelper.isElement(right)) {
            right = (Expression<K>) newElement;
        } else {
            right.replace(newElement);
        }
    }
}
