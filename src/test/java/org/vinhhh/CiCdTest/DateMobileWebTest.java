package org.vinhhh.CiCdTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * MOBILE WEB TESTING
 *
 * Bản chất:
 * Mobile Testing dùng để kiểm tra hệ thống có hoạt động tốt trên thiết bị di động hay không.
 *
 * Vì project này là web application, không phải native Android/iOS app,
 * nên demo theo hướng Mobile Web Testing.
 *
 * Mobile Web Testing nghĩa là:
 * - Mở web bằng trình duyệt
 * - Giả lập kích thước màn hình điện thoại
 * - Thao tác như người dùng mobile
 * - Kiểm tra kết quả hiển thị trên giao diện mobile
 *
 * Flow được test:
 * 1. Spring Boot tự chạy web app trong lúc test
 * 2. Selenium mở Chrome
 * 3. Chrome được chỉnh kích thước giống màn hình điện thoại
 * 4. Selenium nhập day, month, year
 * 5. Selenium bấm nút Check Date
 * 6. Selenium kiểm tra kết quả hiển thị
 *
 * Đoạn code được test:
 * - index.html
 * - DateController
 * - DayTimeChecker
 *
 * Khác với Web E2E Testing:
 * - Web E2E test kiểm tra flow trên trình duyệt desktop.
 * - Mobile Web Test kiểm tra cùng flow nhưng trong kích thước màn hình mobile.
 *
 * Kết quả nhận được:
 * - Pass nếu web vẫn nhập được dữ liệu, bấm được button,
 *   và hiển thị đúng kết quả trên màn hình mobile.
 * - Fail nếu giao diện mobile bị lỗi, không tìm thấy element,
 *   hoặc kết quả hiển thị sai.
 *
 * Cách chạy:
 * mvn -Dtest=DateMobileWebTest test
 *
 * Dependency cần có:
 * - spring-boot-starter-test
 * - selenium-java
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DateMobileWebTest {

    /*
     * Spring Boot tự chọn port còn trống khi chạy test.
     * @LocalServerPort lấy port đó để Selenium truy cập đúng URL.
     */
    @LocalServerPort
    private int port;

    private WebDriver driver;

    /*
     * Tạo URL động dựa trên port thật của test server.
     */
    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    /*
     * Tạo ChromeDriver cho Mobile Web Testing.
     *
     * Local:
     * - Chrome mở lên và được resize giống màn hình điện thoại.
     *
     * GitHub Actions:
     * - Chrome chạy headless vì CI/CD không có màn hình giao diện.
     */
    private WebDriver createMobileDriver() {
        ChromeOptions options = new ChromeOptions();

        /*
         * Nếu chạy trên GitHub Actions thì bật headless mode.
         */
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=390,844");
        }

        WebDriver chromeDriver = new ChromeDriver(options);

        /*
         * Giả lập kích thước màn hình mobile.
         *
         * 390 x 844 gần giống kích thước iPhone 12/13/14.
         */
        chromeDriver.manage().window().setSize(new Dimension(390, 844));

        return chromeDriver;
    }

    /*
     * Đóng browser sau mỗi test case.
     */
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /*
     * Test case 1:
     * Kiểm tra ngày hợp lệ trên giao diện mobile.
     *
     * Input:
     * day = 29
     * month = 2
     * year = 2024
     *
     * Expected:
     * - UI hiển thị 29/2/2024
     * - UI hiển thị Valid date
     */
    @Test
    void mobile_ShouldShowValidDate_WhenInputIsLeapYearDate() throws InterruptedException {
        driver = createMobileDriver();

        driver.get(getBaseUrl());

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

        // Delay 2 giây để dễ theo dõi kết quả khi demo
        Thread.sleep(2000);
    }

    /*
     * Test case 2:
     * Kiểm tra ngày không hợp lệ trên giao diện mobile.
     *
     * Input:
     * day = 31
     * month = 4
     * year = 2025
     *
     * Expected:
     * - UI hiển thị 31/4/2025
     * - UI hiển thị Invalid date
     */
    @Test
    void mobile_ShouldShowInvalidDate_WhenInputIsApril31() throws InterruptedException {
        driver = createMobileDriver();

        driver.get(getBaseUrl());

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

        // Delay 2 giây để dễ theo dõi kết quả khi demo
        Thread.sleep(2000);
    }
}