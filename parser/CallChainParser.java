package parser;

import calls.Call;
import calls.CallChain;
import calls.FilterCall;
import calls.MapCall;
import exceptions.ParsingException;
import expressions.Expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallChainParser extends BaseParser{
    private final static List<String> CALLS = List.of("map", "filter");

    public CallChain parse(String text) {
        setSource(new StringSource(text));
        List<Call> calls = new ArrayList<>();
        while (hasNext()) {
            if (!calls.isEmpty()){
                expect("%>%");
            }
            Call result = parse();
            calls.add(result);
        }
        return new CallChain(calls.toArray(new Call[]{}));
    }

    private Call parse() {
        for (String callType : CALLS) {
            if (test(callType)) {
                expect('{');
                StringBuilder innerExpression = new StringBuilder();
                while (hasNext()) {
                    if (test('}')) {
                        return getCall(callType, innerExpression.toString());
                    }
                    innerExpression.append(ch);
                    nextChar();
                }
                if (test('}')) {
                    return getCall(callType, innerExpression.toString());
                }
            }
        }
        throw new ParsingException();
    }

    private Call getCall(String callType, String innerExpression) {
        Expression expression = new ExpressionParser().parse(innerExpression);
        switch (callType) {
            case "map":
                return new MapCall(expression);
            case "filter":
                return new FilterCall(expression);
            default:
                throw new ParsingException();
        }
    }
}
