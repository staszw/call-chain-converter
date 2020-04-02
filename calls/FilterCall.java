package calls;

import expressions.Expression;

import java.util.ArrayList;
import java.util.List;

public final class FilterCall extends Call<Boolean> {
    public FilterCall(Expression innerExpression) {
        super(innerExpression);
    }

    @Override
    public String getSymbol() {
        return "filter";
    }

    @Override
    public Class<Boolean> getClassOfValue() {
        return Boolean.class;
    }

    @Override
    public Call<Boolean> simplify(Expression<Boolean> expression) {
        return new FilterCall(expression);
    }

    @Override
    public Integer[] apply(Integer[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int current : arr) {
            boolean result = getInnerExpression().evaluate(current);
            if (result) {
                list.add(current);
            }
        }
        return list.toArray(new Integer[]{});
    }
}
