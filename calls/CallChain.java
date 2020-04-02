package calls;

import expressions.*;
import expressions.operations.And;

import java.util.StringJoiner;
import java.util.function.IntBinaryOperator;

public final class CallChain {
    private final Call[] chain;

    public CallChain(Call[] chain) {
        this.chain = chain;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("%>%");
        for (Call current : chain) {
            joiner.add(current.toString());
        }
        return joiner.toString();
    }

    public Integer[] apply(Integer[] array) {
        for (Call current : chain) {
            array = current.apply(array);
        }
        return array;
    }

    public CallChain simplify() {
        Expression<Integer> mapExpression = new Element();
        Expression<Boolean> filterExpression = new BooleanConstant(true);
        for (Call current : chain) {
            if (current instanceof MapCall) {
                Expression<Integer> inner = ((MapCall) current).getInnerExpression();
                if (!inner.equals(ExpressionsHelper.ELEMENT)) {
                    inner.replace(mapExpression);
                    mapExpression = inner;
                }
            } else if (current instanceof FilterCall) {
                Expression<Boolean> addedToFilter = ((FilterCall) current).getInnerExpression();
                addedToFilter.replace(mapExpression);
                filterExpression = new And(filterExpression, addedToFilter);
            }
        }
        return new CallChain(new Call[]{
                new FilterCall(filterExpression.simplify()),
                new MapCall(mapExpression.simplify())
        });
    }
}
