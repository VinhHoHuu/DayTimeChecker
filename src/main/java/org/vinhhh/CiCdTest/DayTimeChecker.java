package org.vinhhh.CiCdTest;

public class DayTimeChecker {
    public boolean isValidDate(int day, int month, int year) {
        if (year <= 0) return false;
        if (month < 1 || month > 12) return false;

        int maxDay;

        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> maxDay = 31;
            case 4, 6, 9, 11 -> maxDay = 30;
            case 2 -> {
                if (isLeapYear(year)) {
                    maxDay = 29;
                } else {
                    maxDay = 28;
                }
            }
            default -> {
                return false;
            }
        }

        return day >= 1 && day <= maxDay;
    }

    public boolean isLeapYear(int year) {
        return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
    }
}
