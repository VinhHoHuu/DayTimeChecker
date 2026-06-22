package org.vinhhh.CiCdTest;

import org.springframework.stereotype.Service;

@Service
public class DayTimeChecker {

    public boolean isValidYear(int year) {
        return year > 0;
    }

    public boolean isValidMonth(int month) {
        return month >= 1 && month <= 12;
    }

    public int dayInMonth(int month, int year) {
        if (!isValidYear(year) || !isValidMonth(month)) {
            return -1;
        }

        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> -1;
        };
    }

    public boolean checkDate(int day, int month, int year) {
        int maxDay = dayInMonth(month, year);

        if (maxDay == -1) {
            return false;
        }

        return day >= 1 && day <= maxDay;
    }

    public boolean isValidDate(int day, int month, int year) {
        return checkDate(day, month, year);
    }

    public boolean isLeapYear(int year) {
        if (!isValidYear(year)) {
            return false;
        }

        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    // Trả về quý của năm: 1 -> 4. Tháng không hợp lệ trả về -1
    public int getQuarter(int month) {
        if (!isValidMonth(month)) {
            return -1;
        }

        return (month - 1) / 3 + 1;
    }

    // Trả về ngày thứ bao nhiêu trong năm.
    // Ví dụ: 01/01/2026 = 1, 01/03/2024 = 61
    public int getDayOfYear(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            return -1;
        }

        int totalDays = 0;

        for (int currentMonth = 1; currentMonth < month; currentMonth++) {
            totalDays += dayInMonth(currentMonth, year);
        }

        return totalDays + day;
    }

    // Trả về số ngày còn lại trong năm, không tính ngày hiện tại.
    public int getRemainingDaysInYear(int day, int month, int year) {
        int dayOfYear = getDayOfYear(day, month, year);

        if (dayOfYear == -1) {
            return -1;
        }

        int totalDaysOfYear = isLeapYear(year) ? 366 : 365;

        return totalDaysOfYear - dayOfYear;
    }

    // So sánh hai ngày:
    // -1: date1 trước date2
    //  0: hai ngày giống nhau
    //  1: date1 sau date2
    // -2: có ngày không hợp lệ
    public int compareDates(
            int day1, int month1, int year1,
            int day2, int month2, int year2
    ) {
        if (!isValidDate(day1, month1, year1)
                || !isValidDate(day2, month2, year2)) {
            return -2;
        }

        if (year1 != year2) {
            return year1 < year2 ? -1 : 1;
        }

        if (month1 != month2) {
            return month1 < month2 ? -1 : 1;
        }

        if (day1 != day2) {
            return day1 < day2 ? -1 : 1;
        }

        return 0;
    }

    public boolean isSameDate(
            int day1, int month1, int year1,
            int day2, int month2, int year2
    ) {
        return compareDates(day1, month1, year1, day2, month2, year2) == 0;
    }
}