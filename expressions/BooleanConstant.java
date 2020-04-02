package expressions;

public final class BooleanConstant implements Expression<Boolean> {
    private final boolean value;

    public BooleanConstant(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public Class<Boolean> getValueClass() {
        return Boolean.class;
    }

    @Override
    public Boolean evaluate(int element) {
        return value;
    }

    @Override
    public Expression<Boolean> simplify() {
        return new BooleanConstant(value);
    }

    @Override
    public void replace(Expression<Integer> newElement) {}

    @Override
    public String toString() {
        if (value) {
            return "0=0";
        } else {
            return "1=0";
        }
    }
}
