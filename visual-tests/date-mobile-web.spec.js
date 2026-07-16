const { test, expect, devices } = require('@playwright/test');

/*
 * MOBILE WEB TESTING VỚI PLAYWRIGHT
 *
 * Bản chất:
 * Kiểm tra xem giao diện và chức năng của ứng dụng web có hoạt động tốt 
 * trên các thiết bị di động hay không bằng cách dùng tính năng Device Emulation 
 * (Giả lập thiết bị) được tích hợp sẵn rất mạnh mẽ của Playwright.
 *
 * Flow được test:
 * 1. Khởi tạo trình duyệt web với cấu hình giả lập chuẩn của iPhone 12
 *    (Bao gồm kích thước màn hình, User-Agent, tỉ lệ pixel, viewport, touch context...).
 * 2. Mở trang web (yêu cầu app Spring Boot phải đang chạy sẵn ở http://localhost:8080).
 * 3. Điền thông tin day, month, year.
 * 4. Bấm nút Check Date.
 * 5. Kiểm tra kết quả hiển thị trên màn hình.

 * npx playwright test visual-tests/date-mobile-web.spec.js --headed
 */

// Áp dụng cấu hình của thiết bị "iPhone 12" cho toàn bộ các test case trong file này
test.use({
  ...devices['iPhone 12'],
});

test('Mobile UI - should show valid date when input is leap year date (iPhone 12)', async ({ page }) => {
  // 1. Mở trang web (Truy cập bằng IP/Port của server local)
  await page.goto('http://localhost:8080');

  // 2. Playwright sẽ giả lập thao tác chạm để điền form giống như điện thoại
  await page.locator('input[name="day"]').fill('29');
  await page.locator('input[name="month"]').fill('2');
  await page.locator('input[name="year"]').fill('2024');

  // 3. Bấm nút "Check Date"
  await page.locator('button').click();

  // 4. Chờ phần tử div chứa class "result" hiển thị (thay cho WebDriverWait bên Selenium)
  const result = page.locator('.result');
  await expect(result).toBeVisible();

  // Kiểm tra kết quả text hiển thị
  await expect(result).toContainText('29/2/2024');
  await expect(result).toContainText('Valid date');

  // Delay 2 giây để dễ quan sát giao diện khi demo
  await page.waitForTimeout(2000);
});

test('Mobile UI - should show invalid date when input is April 31 (iPhone 12)', async ({ page }) => {
  // 1. Mở trang web
  await page.goto('http://localhost:8080');

  // 2. Nhập thông tin ngày sai (31 tháng 4)
  await page.locator('input[name="day"]').fill('31');
  await page.locator('input[name="month"]').fill('4');
  await page.locator('input[name="year"]').fill('2025');

  // 3. Bấm nút "Check Date"
  await page.locator('button').click();

  // 4. Chờ và kiểm tra phần tử kết quả hiển thị
  const result = page.locator('.result');
  await expect(result).toBeVisible();

  // Kiểm tra text cảnh báo ngày sai
  await expect(result).toContainText('31/4/2025');
  await expect(result).toContainText('Invalid date');

  // Delay 2 giây để dễ quan sát giao diện khi demo
  await page.waitForTimeout(2000);
});
