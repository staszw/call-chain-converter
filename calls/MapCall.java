package calls;

import expressions.Expression;

import java.util.Collections;

public final class MapCall extends Call<Integer> {

    public MapCall(Expression innerExpression) {
        super(innerExpression);
    }

    @Override
    public String getSymbol() {
        return "map";
    }

    @Override
    public Class<Integer> getClassOfValue() {
        return Integer.class;
    }

    @Override
    public Call<Integer> simplify(Expression<Integer> expression) {
        return new MapCall(expression);
    }

    @Override
    public Integer[] apply(Integer[] arr) {
        Integer[] newArray = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = getInnerExpression().evaluate(arr[i]);
        }
        return newArray;
    }
}
