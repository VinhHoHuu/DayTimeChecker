package org.vinhhh.CiCdTest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DateRestController {
    /*
     * API TESTING TARGET
     *
     * Controller này tạo REST API để test bằng Postman.
     *
     * API endpoint:
     * GET /api/check-date?day=29&month=2&year=2024
     *
     * Bản chất API Testing:
     * API Testing kiểm tra request gửi vào server và response trả về từ server.
     * Người test không kiểm tra giao diện web, mà kiểm tra dữ liệu trả về,
     * thường là JSON.
     *
     * Đoạn được test:
     * - URL endpoint /api/check-date
     * - Query parameters: day, month, year
     * - Response JSON gồm: day, month, year, valid, message
     *
     * Tool dùng để demo:
     * Postman
     *
     * Kết quả nhận được:
     * - Nếu nhập 29/2/2024: valid = true, message = "Valid date"
     * - Nếu nhập 31/4/2025: valid = false, message = "Invalid date"
     *
     * Dependency cần có:
     * spring-boot-starter-web
     */
    private final DayTimeChecker dayTimeChecker;

    public DateRestController(DayTimeChecker dayTimeChecker) {
        this.dayTimeChecker = dayTimeChecker;
    }

    @GetMapping("/api/check-date")
    public DateCheckResponse checkDate(int day, int month, int year) {
        boolean valid = dayTimeChecker.isValidDate(day, month, year);

        String message;
        if (valid) {
            message = "Valid date";
        } else {
            message = "Invalid date";
        }

        return new DateCheckResponse(day, month, year, valid, message);
    }
}