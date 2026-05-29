package org.vinhhh.CiCdTest;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DayTimeChecker checker = new DayTimeChecker();

        System.out.println("DayTimeChecker Demo");
        System.out.println("25/05/2026: " + checker.isValidDate(25, 5, 2026));
        System.out.println("32/01/2026: " + checker.isValidDate(32, 1, 2026));
        System.out.println("29/02/2024: " + checker.isValidDate(29, 2, 2024));
        System.out.println("29/02/2025: " + checker.isValidDate(29, 2, 2025));
    }
}