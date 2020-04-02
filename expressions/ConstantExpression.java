package expressions;

import java.util.Objects;

public final class ConstantExpression implements Expression<Integer> {
    private final int value;

    public ConstantExpression(String value) {
        this.value = Integer.parseInt(value);
    }
    public ConstantExpression(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ConstantExpression &&
                value == (((ConstantExpression) obj).value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }


    @Override
    public Class<Integer> getValueClass() {
        return Integer.class;
    }

    @Override
    public Integer evaluate(int x) {
        return value;
    }

    @Override
    public ConstantExpression simplify() {
        return new ConstantExpression(value);
    }

    @Override
    public void replace(Expression<Integer> newElement) {
    }
}
