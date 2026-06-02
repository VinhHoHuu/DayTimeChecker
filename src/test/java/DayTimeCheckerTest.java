import org.junit.jupiter.api.Test;
import org.vinhhh.CiCdTest.DayTimeChecker;

import static org.junit.jupiter.api.Assertions.*;

class DayTimeCheckerTest {

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