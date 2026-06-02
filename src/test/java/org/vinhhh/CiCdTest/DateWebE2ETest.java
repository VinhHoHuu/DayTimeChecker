package org.vinhhh.CiCdTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
//mvn -Dtest=DateWebE2ETest test đây là lệnh test E2E trên cmd
class DateWebE2ETest {
    /*
     * WEB E2E TESTING
     *
     * Bản chất:
     * Web E2E Testing kiểm thử toàn bộ luồng hoạt động từ giao diện đến xử lý logic.
     * Nó mô phỏng hành vi của người dùng thật trên trình duyệt.
     *
     * Flow được test:
     * 1. Mở trang web http://localhost:8080
     * 2. Nhập day, month, year vào form
     * 3. Bấm nút Check Date
     * 4. Đợi kết quả xuất hiện
     * 5. Kiểm tra text hiển thị trên giao diện
     *
     * Đoạn code được test:
     * - index.html
     * - DateController
     * - DayTimeChecker
     *
     * Tool dùng:
     * Selenium WebDriver + ChromeDriver + JUnit
     *
     * Kết quả nhận được:
     * - Nếu giao diện hiển thị đúng "Valid date" hoặc "Invalid date": test pass.
     * - Nếu form lỗi, controller lỗi, hoặc UI không hiển thị đúng: test fail.
     *
     * Cách chạy:
     * Bước 1: Chạy Spring Boot app trước.
     * Bước 2: Mở terminal khác và chạy:
     * mvn -Dtest=DateWebE2ETest test
     *
     * Dependency cần có:
     * selenium-java
     * spring-boot-starter-test
     */
    private WebDriver driver;

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void shouldShowValidDate_WhenInputIsLeapYearDate() {
        driver = new ChromeDriver();

        driver.get("http://localhost:8080");

        driver.findElement(By.name("day")).sendKeys("29");
        driver.findElement(By.name("month")).sendKeys("2");
        driver.findElement(By.name("year")).sendKeys("2024");

        driver.findElement(By.tagName("button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("result"))
        );

        assertTrue(result.getText().contains("29/2/2024"));
        assertTrue(result.getText().contains("Valid date"));
    }

    @Test
    void shouldShowInvalidDate_WhenInputIsApril31() {
        driver = new ChromeDriver();

        driver.get("http://localhost:8080");

        driver.findElement(By.name("day")).sendKeys("31");
        driver.findElement(By.name("month")).sendKeys("4");
        driver.findElement(By.name("year")).sendKeys("2025");

        driver.findElement(By.tagName("button")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("result"))
        );

        assertTrue(result.getText().contains("31/4/2025"));
        assertTrue(result.getText().contains("Invalid date"));
    }
}