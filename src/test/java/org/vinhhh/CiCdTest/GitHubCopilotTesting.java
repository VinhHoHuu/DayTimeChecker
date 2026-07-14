package org.vinhhh.CiCdTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * AI-ASSISTED TESTING (GitHub Copilot)
 *
 * Lệnh chạy:
 * mvn -Dtest=GitHubCopilotTesting test
 */
public class GitHubCopilotTesting {
    private final DayTimeChecker checker = new DayTimeChecker();

    // Test case 1: Kiểm tra hàm isLeapYear với năm nhuận
    @Test
    void testIsLeapYear_LeapYear_ReturnTrue() {
        assertTrue(checker.isLeapYear(2020));
    }

    // Test case 2: Kiểm tra hàm isLeapYear với năm không phải năm nhuận
    @Test
    void testIsLeapYear_NonLeapYear_ReturnFalse() {
        assertFalse(checker.isLeapYear(2019));
    }

    // Test case 3: Kiểm tra hàm dayInMonth với tháng không hợp lệ
    @Test
    void testDayInMonth_InvalidMonth_ReturnMinus1() {
        assertEquals(-1, checker.dayInMonth(13, 2021));
    }

    // Test case 4: Kiểm tra hàm checkDate với ngày hợp lệ
    @Test
    void testCheckDate_ValidDate_ReturnTrue() {
        assertTrue(checker.checkDate(15, 8, 2021));
    }

    // Test case 5: Kiểm tra hàm checkDate với ngày không hợp lệ
    @Test
    void testCheckDate_InvalidDate_ReturnFalse() {
        assertFalse(checker.checkDate(31, 4, 2021));
    }
}
