import org.junit.jupiter.api.Test;
import org.vinhhh.CiCdTest.DayTimeChecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class DayTimeCheckerTest {
    private final DayTimeChecker checker = new DayTimeChecker();

    @Test
    void validNormalDateShouldReturnTrue() {
        assertTrue(checker.isValidDate(25, 5, 2026));
    }

    @Test
    void dayGreaterThanMaxDayShouldReturnFalse() {
        assertFalse(checker.isValidDate(32, 1, 2026));
    }

    @Test
    void invalidMonthShouldReturnFalse() {
        assertFalse(checker.isValidDate(10, 13, 2026));
    }

    @Test
    void invalidYearShouldReturnFalse() {
        assertFalse(checker.isValidDate(10, 10, 0));
    }

    @Test
    void february29InLeapYearShouldReturnTrue() {
        assertTrue(checker.isValidDate(29, 2, 2024));
    }

    @Test
    void february29InNonLeapYearShouldReturnFalse() {
        assertFalse(checker.isValidDate(29, 2, 2025));
    }

    @Test
    void april31ShouldReturnFalse() {
        assertFalse(checker.isValidDate(31, 4, 2026));
    }

    @Test
    void december31ShouldReturnTrue() {
        assertTrue(checker.isValidDate(31, 12, 2026));
    }

    @Test
    void dayZeroShouldReturnFalse() {
        assertFalse(checker.isValidDate(0, 5, 2026));
    }

    @Test
    void centuryYear1900ShouldNotBeLeapYear() {
        assertFalse(checker.isLeapYear(1900));
    }

    @Test
    void year2000ShouldBeLeapYear() {
        assertTrue(checker.isLeapYear(2000));
    }
}
