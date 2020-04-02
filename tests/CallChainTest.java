package tests;

import calls.Call;
import calls.CallChain;
import calls.FilterCall;
import calls.MapCall;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CallChainTest {
    private static final int TEST_COUNT = 1000;
    private static final int MAX_LENGTH_OF_ARRAY = 100;
    private static final Random RANDOM = new Random();
    private static final List<String> CALLS = List.of("map", "filter");
    public static void main(String[] args) {
        Integer[] array = new Integer[MAX_LENGTH_OF_ARRAY];
        for (int i = 0; i < MAX_LENGTH_OF_ARRAY; i++) {
            array[i] = RANDOM.nextInt();
        }
        for (int i = 0; i < TEST_COUNT; i++) {
            CallChain test = generateTest();
            Integer[] expected = test.apply(array);
            try {
                Integer[] found = test.simplify().apply(array);
                if (!Arrays.equals(found, expected)) {
                    System.out.println("Test failed. CallChain: " + test + ". Array: " + Arrays.toString(array));
                    System.out.println("Expected: " + Arrays.toString(expected) + ".");
                    System.out.println("Found: " + Arrays.toString(found) + ".");
                    return;
                }
            } catch (Exception e) {
                System.out.println("Unexpected error. CallChain: " + test + ". Array: " + Arrays.toString(array));
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("Total number of tests: " + TEST_COUNT + ". Passed tests: " + TEST_COUNT + ".");
    }

    public static CallChain generateTest() {
        int callCount = RANDOM.nextInt(MAX_LENGTH_OF_ARRAY);
        Call[] calls = new Call[callCount];
        for (int i = 0; i < callCount; i++) {
            Call current = null;
            int callType = RANDOM.nextInt(2);
            while (current == null) {
                try {
                    switch (CALLS.get(callType)) {
                        case "map":
                            current = new MapCall(ParserTest.generateTest());
                        case "filter":
                            current = new FilterCall(ParserTest.generateTest());
                    }
                } catch (Exception ignored) {
                }
            }
            calls[i] = current;
        }
        return new CallChain(calls);
    }
}
