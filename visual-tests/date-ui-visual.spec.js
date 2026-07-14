const { test, expect } = require('@playwright/test');

/*
 * VISUAL REGRESSION TESTING
 *
 * Bản chất:
 * Visual Regression Testing kiểm tra giao diện có bị thay đổi ngoài ý muốn hay không.
 *
 * Cách hoạt động:
 * 1. Mở trang web
 * 2. Chụp screenshot giao diện hiện tại
 * 3. So sánh với screenshot chuẩn đã lưu trước đó
 * 4. Nếu giao diện khác screenshot chuẩn quá nhiều thì test fail
 *
 * Tool dùng:
 * - Playwright screenshot testing
 *
 * Cách chạy lần đầu để tạo ảnh chuẩn:
 * npx playwright test visual-tests/date-ui-visual.spec.js --update-snapshots
 *
 * Cách chạy các lần sau để kiểm tra giao diện:
 * npx playwright test visual-tests/date-ui-visual.spec.js
 *
 * Cách chạy xem debug từng phần
 * npx playwright test visual-tests/date-ui-visual.spec.js --headed --debug
    --headed  = mở Chrome lên cho mày thấy
    --debug   = dừng từng bước, mở Playwright Inspector


    1/Tạo ảnh chuẩn đầu tiên để đối chiếu
    npx playwright test visual-tests/date-ui-visual.spec.js --update-snapshots

    2/Chạy test để kiểm tra
    npx playwright test visual-tests/date-ui-visual.spec.js

 */

test('homepage should match visual snapshot', async ({ page }) => {
    await page.goto('http://localhost:8080');

    await expect(page).toHaveScreenshot('homepage.png', {
        fullPage: true,
        maxDiffPixelRatio: 0.02
    });
});

test('valid date result should match visual snapshot', async ({ page }) => {
    await page.goto('http://localhost:8080');

    await page.locator('input[name="day"]').fill('29');
    await page.locator('input[name="month"]').fill('2');
    await page.locator('input[name="year"]').fill('2024');

    await page.locator('button').click();

    await expect(page).toHaveScreenshot('valid-date-result.png', {
        fullPage: true,
        maxDiffPixelRatio: 0.02
    });
});

test('invalid date result should match visual snapshot', async ({ page }) => {
    await page.goto('http://localhost:8080');

    await page.locator('input[name="day"]').fill('31');
    await page.locator('input[name="month"]').fill('4');
    await page.locator('input[name="year"]').fill('2025');

    await page.locator('button').click();

    await expect(page).toHaveScreenshot('invalid-date-result.png', {
        fullPage: true,
        maxDiffPixelRatio: 0.02
    });
});