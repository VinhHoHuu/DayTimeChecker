package org.vinhhh.CiCdTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTimeCheckerTest {

    private final DayTimeChecker checker = new DayTimeChecker();

    /*
     * ============================================================
     * TEST CASES FOR DayInMonth FUNCTION
     * ============================================================
     */

    // UTCID01 - February in leap year
    @Test
    void UTCID01_DayInMonth_FebruaryLeapYear_Return29() {
        assertEquals(29, checker.dayInMonth(2, 2020));
    }

    // UTCID02 - February in normal year
    @Test
    void UTCID02_DayInMonth_FebruaryNormalYear_Return28() {
        assertEquals(28, checker.dayInMonth(2, 2021));
    }

    // UTCID03 - Month has 31 days
    @Test
    void UTCID03_DayInMonth_January_Return31() {
        assertEquals(31, checker.dayInMonth(1, 2021));
    }

    // UTCID04 - Month has 30 days
    @Test
    void UTCID04_DayInMonth_April_Return30() {
        assertEquals(30, checker.dayInMonth(4, 2021));
    }

    // UTCID05 - March has 31 days
    @Test
    void UTCID05_DayInMonth_March_Return31() {
        assertEquals(31, checker.dayInMonth(3, 2020));
    }

    // UTCID06 - May has 31 days
    @Test
    void UTCID06_DayInMonth_May_Return31() {
        assertEquals(31, checker.dayInMonth(5, 2020));
    }

    // UTCID07 - June has 30 days
    @Test
    void UTCID07_DayInMonth_June_Return30() {
        assertEquals(30, checker.dayInMonth(6, 2019));
    }

    // UTCID08 - July has 31 days
    @Test
    void UTCID08_DayInMonth_July_Return31() {
        assertEquals(31, checker.dayInMonth(7, 2019));
    }

    // UTCID09 - August has 31 days
    @Test
    void UTCID09_DayInMonth_August_Return31() {
        assertEquals(31, checker.dayInMonth(8, 2021));
    }

    // UTCID10 - September has 30 days
    @Test
    void UTCID10_DayInMonth_September_Return30() {
        assertEquals(30, checker.dayInMonth(9, 2021));
    }

    // UTCID11 - October has 31 days
    @Test
    void UTCID11_DayInMonth_October_Return31() {
        assertEquals(31, checker.dayInMonth(10, 2021));
    }

    // UTCID12 - November has 30 days
    @Test
    void UTCID12_DayInMonth_November_Return30() {
        assertEquals(30, checker.dayInMonth(11, 2021));
    }

    // UTCID13 - December has 31 days
    @Test
    void UTCID13_DayInMonth_December_Return31() {
        assertEquals(31, checker.dayInMonth(12, 2021));
    }

    // UTCID14 - Invalid month smaller than 1
    @Test
    void UTCID14_DayInMonth_InvalidMonthZero_ReturnMinus1() {
        assertEquals(-1, checker.dayInMonth(0, 2019));
    }

    // UTCID15 - Invalid month greater than 12
    @Test
    void UTCID15_DayInMonth_InvalidMonthFifteen_ReturnMinus1() {
        assertEquals(-1, checker.dayInMonth(15, 2019));
    }


    /*
     * ============================================================
     * TEST CASES FOR CheckDate FUNCTION
     * ============================================================
     */

    // UTCID01 - Valid date: 29/2/2000
    @Test
    void UTCID01_CheckDate_ValidLeapYearDate_ReturnTrue() {
        assertTrue(checker.checkDate(29, 2, 2000));
    }

    // UTCID02 - Invalid date: 29/2/2009
    @Test
    void UTCID02_CheckDate_InvalidFebruary29NormalYear_ReturnFalse() {
        assertFalse(checker.checkDate(29, 2, 2009));
    }

    // UTCID03 - Invalid date: 31/2/2020
    @Test
    void UTCID03_CheckDate_InvalidFebruary31_ReturnFalse() {
        assertFalse(checker.checkDate(31, 2, 2020));
    }

    // UTCID04 - Valid date: 30/3/2000
    @Test
    void UTCID04_CheckDate_ValidMarch30_ReturnTrue() {
        assertTrue(checker.checkDate(30, 3, 2000));
    }

    // UTCID05 - Invalid date: 31/4/2009
    @Test
    void UTCID05_CheckDate_InvalidApril31_ReturnFalse() {
        assertFalse(checker.checkDate(31, 4, 2009));
    }

    // UTCID06 - Valid date: 29/3/2000
    @Test
    void UTCID06_CheckDate_ValidMarch29_ReturnTrue() {
        assertTrue(checker.checkDate(29, 3, 2000));
    }

    // UTCID07 - Valid date: 29/4/2020
    @Test
    void UTCID07_CheckDate_ValidApril29_ReturnTrue() {
        assertTrue(checker.checkDate(29, 4, 2020));
    }

    // UTCID08 - Valid date: 30/4/2026
    @Test
    void UTCID08_CheckDate_ValidApril30_ReturnTrue() {
        assertTrue(checker.checkDate(30, 4, 2026));
    }

    // UTCID09 - Invalid date: 30/2/2020
    @Test
    void UTCID09_CheckDate_InvalidFebruary30_ReturnFalse() {
        assertFalse(checker.checkDate(30, 2, 2020));
    }

    // UTCID10 - Valid date: 28/3/2014
    @Test
    void UTCID10_CheckDate_ValidMarch28_ReturnTrue() {
        assertTrue(checker.checkDate(28, 3, 2014));
    }

    // UTCID11 - Invalid date: 31/4/2011
    @Test
    void UTCID11_CheckDate_InvalidApril31_ReturnFalse() {
        assertFalse(checker.checkDate(31, 4, 2011));
    }

    // UTCID12 - Valid date: 31/3/2018
    @Test
    void UTCID12_CheckDate_ValidMarch31_ReturnTrue() {
        assertTrue(checker.checkDate(31, 3, 2018));
    }

    // UTCID13 - Valid date: 28/2/2004
    @Test
    void UTCID13_CheckDate_ValidFebruary28_ReturnTrue() {
        assertTrue(checker.checkDate(28, 2, 2004));
    }

    // UTCID14 - Valid date: 31/3/2011
    @Test
    void UTCID14_CheckDate_ValidMarch31_ReturnTrue() {
        assertTrue(checker.checkDate(31, 3, 2011));
    }

    // UTCID15 - Invalid date: 29/4/2014
    // Actually 29/4/2014 is valid, so this test expects true.
    @Test
    void UTCID15_CheckDate_ValidApril29_ReturnTrue() {
        assertTrue(checker.checkDate(29, 4, 2014));
    }
}