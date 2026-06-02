package org.vinhhh.CiCdTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayTimeCheckerTest {
    /*
     * UNIT TESTING
     *
     * Bản chất:
     * Unit Testing dùng để kiểm thử từng hàm hoặc từng class nhỏ một cách độc lập.
     * Ở đây, test trực tiếp class DayTimeChecker mà không cần chạy web, không cần API,
     * không cần browser và không cần database.
     *
     * Đoạn code được test:
     * - DayTimeChecker.isValidDate()
     * - DayTimeChecker.isLeapYear()
     *
     * Mục tiêu:
     * Kiểm tra logic xử lý ngày hợp lệ, ngày không hợp lệ và năm nhuận.
     *
     * Kết quả nhận được:
     * - Nếu logic đúng: test pass, Maven hiện BUILD SUCCESS.
     * - Nếu logic sai: test fail, Maven báo lỗi ở test case bị sai.
     *
     * Cách chạy:
     * mvn test
     *
     * Dependency cần có:
     * spring-boot-starter-test
     */

    private final DayTimeChecker dayTimeChecker = new DayTimeChecker();

    @Test
    void isValidDate_ShouldReturnTrue_WhenDateIsValid() {
        boolean result = dayTimeChecker.isValidDate(15, 6, 2025);

        assertTrue(result);
    }

    @Test
    void isValidDate_ShouldReturnFalse_WhenDayIsGreaterThan31() {
        boolean result = dayTimeChecker.isValidDate(32, 1, 2025);

        assertFalse(result);
    }

    @Test
    void isValidDate_ShouldReturnFalse_WhenMonthIsInvalid() {
        boolean result = dayTimeChecker.isValidDate(10, 13, 2025);

        assertFalse(result);
    }

    @Test
    void isValidDate_ShouldReturnFalse_WhenYearIsInvalid() {
        boolean result = dayTimeChecker.isValidDate(10, 5, 0);

        assertFalse(result);
    }

    @Test
    void isValidDate_ShouldReturnTrue_WhenFebruary29InLeapYear() {
        boolean result = dayTimeChecker.isValidDate(29, 2, 2024);

        assertTrue(result);
    }

    @Test
    void isValidDate_ShouldReturnFalse_WhenFebruary29InNonLeapYear() {
        boolean result = dayTimeChecker.isValidDate(29, 2, 2023);

        assertFalse(result);
    }

    @Test
    void isValidDate_ShouldReturnFalse_WhenAprilHas31Days() {
        boolean result = dayTimeChecker.isValidDate(31, 4, 2025);

        assertFalse(result);
    }

    @Test
    void isLeapYear_ShouldReturnTrue_WhenYearDivisibleBy400() {
        boolean result = dayTimeChecker.isLeapYear(2000);

        assertTrue(result);
    }

    @Test
    void isLeapYear_ShouldReturnFalse_WhenYearDivisibleBy100ButNot400() {
        boolean result = dayTimeChecker.isLeapYear(1900);

        assertFalse(result);
    }

    @Test
    void isLeapYear_ShouldReturnTrue_WhenYearDivisibleBy4ButNot100() {
        boolean result = dayTimeChecker.isLeapYear(2024);

        assertTrue(result);
    }
}