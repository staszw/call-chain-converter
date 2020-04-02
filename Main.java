import calls.CallChain;
import parser.CallChainParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String text = "filter{(1222050721>element)}";
        CallChain callChain = new CallChainParser().parse(text).simplify();
        System.out.println(callChain);
    }
}
