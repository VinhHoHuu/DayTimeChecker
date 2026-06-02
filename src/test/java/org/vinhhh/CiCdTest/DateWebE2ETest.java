package org.vinhhh.CiCdTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * WEB E2E TESTING
 *
 * Bản chất:
 * Web E2E Testing dùng để kiểm thử toàn bộ luồng hoạt động của hệ thống
 * giống như một người dùng thật đang thao tác trên trình duyệt.
 *
 * Trong project này, E2E test sẽ kiểm tra luồng:
 * 1. Mở trang web DayTimeChecker
 * 2. Nhập day, month, year vào form
 * 3. Bấm nút Check Date
 * 4. Đợi kết quả hiển thị trên giao diện
 * 5. Kiểm tra text kết quả có đúng hay không
 *
 * Đoạn code được test:
 * - index.html: giao diện form nhập ngày tháng năm
 * - DateController: xử lý request từ form
 * - DayTimeChecker: xử lý logic kiểm tra ngày hợp lệ
 *
 * Tool sử dụng:
 * - Selenium WebDriver
 * - ChromeDriver
 * - JUnit 5
 *
 * Kết quả nhận được:
 * - Test pass nếu browser tự nhập form và giao diện hiển thị đúng kết quả.
 * - Test fail nếu không mở được Chrome, không tìm thấy input/button/result,
 *   hoặc kết quả hiển thị sai.
 *
 * Cách chạy local:
 * mvn -Dtest=DateWebE2ETest test
 *
 * Dependency cần có trong pom.xml:
 * - spring-boot-starter-test
 * - selenium-java
 */

/*
 * Annotation này giúp Spring Boot tự chạy web app khi test.
 *
 * DEFINED_PORT nghĩa là app sẽ chạy ở port cố định 8080.
 * Nhờ vậy Selenium có thể mở đúng URL:
 * http://localhost:8080
 *
 * Nếu không có annotation này, khi chạy E2E test trên GitHub Actions,
 * app có thể chưa chạy nên Selenium không truy cập được trang web.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DateWebE2ETest {

    /*
     * WebDriver là đối tượng đại diện cho trình duyệt.
     * Trong test này, driver sẽ điều khiển Chrome:
     * mở web, nhập dữ liệu, bấm nút và đọc kết quả.
     */
    private WebDriver driver;

    /*
     * Method này dùng để tạo ChromeDriver.
     *
     * Điểm quan trọng:
     * - Khi chạy local trên máy cá nhân, Chrome có thể mở bình thường.
     * - Khi chạy trên GitHub Actions, môi trường là Linux server,
     *   thường không có màn hình giao diện.
     *
     * Vì vậy nếu phát hiện đang chạy trong CI/CD,
     * Chrome phải chạy ở chế độ headless.
     */
    private WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();

        /*
         * GitHub Actions tự tạo biến môi trường CI.
         *
         * Nếu System.getenv("CI") != null,
         * nghĩa là test đang chạy trên CI/CD pipeline.
         */
        if (System.getenv("CI") != null) {

            /*
             * --headless=new:
             * Chạy Chrome không cần mở cửa sổ giao diện.
             * Bắt buộc khi chạy trên GitHub Actions.
             */
            options.addArguments("--headless=new");

            /*
             * --no-sandbox:
             * Giúp Chrome chạy ổn định hơn trong môi trường Linux CI.
             */
            options.addArguments("--no-sandbox");

            /*
             * --disable-dev-shm-usage:
             * Tránh lỗi thiếu bộ nhớ chia sẻ trong container/runner.
             */
            options.addArguments("--disable-dev-shm-usage");

            /*
             * --disable-gpu:
             * Tắt GPU vì môi trường CI thường không cần hoặc không hỗ trợ GPU.
             */
            options.addArguments("--disable-gpu");

            /*
             * --window-size:
             * Đặt kích thước màn hình giả lập để giao diện render ổn định.
             */
            options.addArguments("--window-size=1920,1080");
        }

        /*
         * Tạo ChromeDriver với các options ở trên.
         * Nếu chạy local thì Chrome mở bình thường.
         * Nếu chạy CI thì Chrome chạy headless.
         */
        return new ChromeDriver(options);
    }

    /*
     * @AfterEach chạy sau mỗi test case.
     *
     * Mục đích:
     * Đóng trình duyệt sau khi test xong để tránh Chrome chạy ngầm.
     */
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /*
     * Test case 1:
     * Kiểm tra ngày 29/2/2024.
     *
     * Vì 2024 là năm nhuận, ngày 29/2/2024 là ngày hợp lệ.
     *
     * Expected result:
     * - Giao diện hiển thị: 29/2/2024
     * - Giao diện hiển thị: Valid date
     */
    @Test
    void shouldShowValidDate_WhenInputIsLeapYearDate() {
        driver = createDriver();

        /*
         * Mở trang web chính của hệ thống.
         */
        driver.get("http://localhost:8080");

        /*
         * Selenium tìm các input theo thuộc tính name trong index.html,
         * sau đó tự nhập dữ liệu giống người dùng thật.
         */
        driver.findElement(By.name("day")).sendKeys("29");
        driver.findElement(By.name("month")).sendKeys("2");
        driver.findElement(By.name("year")).sendKeys("2024");

        /*
         * Selenium tìm button và tự click.
         */
        driver.findElement(By.tagName("button")).click();

        /*
         * Sau khi click, trang cần thời gian để gửi request và render kết quả.
         * WebDriverWait giúp Selenium đợi tối đa 5 giây
         * cho đến khi div class="result" xuất hiện.
         */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("result"))
        );

        /*
         * Kiểm tra kết quả hiển thị trên giao diện.
         *
         * Nếu text trên UI có chứa "29/2/2024" và "Valid date",
         * test sẽ pass.
         */
        assertTrue(result.getText().contains("29/2/2024"));
        assertTrue(result.getText().contains("Valid date"));
    }

    /*
     * Test case 2:
     * Kiểm tra ngày 31/4/2025.
     *
     * Tháng 4 chỉ có 30 ngày,
     * nên 31/4/2025 là ngày không hợp lệ.
     *
     * Expected result:
     * - Giao diện hiển thị: 31/4/2025
     * - Giao diện hiển thị: Invalid date
     */
    @Test
    void shouldShowInvalidDate_WhenInputIsApril31() {
        driver = createDriver();

        /*
         * Mở trang web chính của hệ thống.
         */
        driver.get("http://localhost:8080");

        /*
         * Nhập dữ liệu ngày không hợp lệ vào form.
         */
        driver.findElement(By.name("day")).sendKeys("31");
        driver.findElement(By.name("month")).sendKeys("4");
        driver.findElement(By.name("year")).sendKeys("2025");

        /*
         * Click nút Check Date.
         */
        driver.findElement(By.tagName("button")).click();

        /*
         * Đợi kết quả xuất hiện trên giao diện.
         */
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("result"))
        );

        /*
         * Kiểm tra kết quả hiển thị trên giao diện.
         *
         * Nếu text trên UI có chứa "31/4/2025" và "Invalid date",
         * test sẽ pass.
         */
        assertTrue(result.getText().contains("31/4/2025"));
        assertTrue(result.getText().contains("Invalid date"));
    }
}