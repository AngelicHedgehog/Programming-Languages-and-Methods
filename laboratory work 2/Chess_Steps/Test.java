package com.company;

public class Test {
    public static void main(String[] args) {
        ChessSteps Steps = new ChessSteps();
        Steps.newStep("e2", "e4"); Steps.newStep("e7", "e5");
        Steps.newStep("g1", "f3"); Steps.newStep("b8", "c6");
        Steps.newStep("f3", "e4"); Steps.newStep("f8", "c5");//фигура на f3(g1) ест фигуру на e4(e2)
        Steps.newStep("h2", "e4"); Steps.newStep("g7", "g1");//фигура на h2(h2) ест фигуру на e4(g1)
        Steps.newStep("b1", "c3"); Steps.newStep("g8", "f6");
        Steps.newStep("d2", "d3"); Steps.newStep("d7", "d6");
        Steps.newStep("c1", "g5"); Steps.newStep("c8", "g4");
        System.out.println(Steps.wereNow("e2", 0));
        System.out.println(Steps.wereNow("e2", 1));
        System.out.println(Steps.wereNow("e2", 3));
        System.out.println(Steps.wereNow("g1", 0));
        System.out.println(Steps.wereNow("g1", 2));
        System.out.println(Steps.wereNow("g1", 4));
        System.out.println(Steps);
    }
}
