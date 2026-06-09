package org.vinhhh.CiCdTest;

import org.springframework.stereotype.Service;

@Service
public class DayTimeChecker {

    /*
     * UNIT TESTING TARGET
     *
     * Class này chứa business logic chính của hệ thống:
     * kiểm tra ngày/tháng/năm có hợp lệ hay không.
     *
     * Đây là phần phù hợp nhất để demo Unit Testing vì:
     * - Không cần chạy Spring Boot app
     * - Không cần mở web
     * - Không cần gọi API
     * - Chỉ test trực tiếp từng method nhỏ
     *
     * Test file liên quan:
     * src/test/java/org/vinhhh/CiCdTest/DayTimeCheckerTest.java
     */

        public int dayInMonth(int month, int year) {
            if (year <= 0) return -1;
            if (month < 1 || month > 12) return -1;

            return switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> 31;
                case 4, 6, 9, 11 -> 30;
                case 2 -> isLeapYear(year) ? 29 : 28;
                default -> -1;
            };
        }

        public boolean checkDate(int day, int month, int year) {
            int maxDay = dayInMonth(month, year);

            if (maxDay == -1) return false;

            return day >= 1 && day <= maxDay;
        }

        public boolean isValidDate(int day, int month, int year) {
            return checkDate(day, month, year);
        }

        public boolean isLeapYear(int year) {
            return year % 400 == 0 || year % 4 == 0 && year % 100 != 0;
        }
    }
