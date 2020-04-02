package calls;

import exceptions.TypeMismatchException;
import expressions.Expression;

public abstract class Call<V> {
    private final Expression<V> innerExpression;

    public Call(Expression innerExpression) {
        if (getClassOfValue().isAssignableFrom(innerExpression.getValueClass())) {
            this.innerExpression = (Expression<V>) innerExpression;
        } else {
            throw new TypeMismatchException();
        }
    }

    public abstract String getSymbol();

    public abstract Class<V> getClassOfValue();

    public abstract Call<V> simplify(Expression<V> expression);

    public abstract Integer[] apply(Integer[] arr);

    public Call<V> simplify() {
        return simplify(innerExpression.simplify());
    }

    @Override
    public String toString() {
        return getSymbol() + "{" + innerExpression + "}";
    }

    public Expression<V> getInnerExpression() {
        return innerExpression;
    }
}
