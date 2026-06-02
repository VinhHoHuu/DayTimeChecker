package org.vinhhh.CiCdTest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DateController {
    /*
     * WEB E2E TESTING TARGET
     *
     * Controller này xử lý flow trên giao diện web:
     * User mở trang web -> nhập ngày tháng năm -> bấm Check Date
     * -> server xử lý -> trả kết quả về lại giao diện.
     *
     * Bản chất Web E2E Testing:
     * E2E Testing kiểm thử toàn bộ luồng hoạt động của hệ thống giống người dùng thật.
     * Test sẽ mở browser, nhập dữ liệu, bấm nút và kiểm tra kết quả hiển thị trên UI.
     *
     * Đoạn được test:
     * - GET /
     * - POST /check
     * - Form trong file index.html
     * - Kết quả hiển thị Valid date hoặc Invalid date
     *
     * Test file liên quan:
     * src/test/java/org/vinhhh/CiCdTest/DateWebE2ETest.java
     *
     * Tool dùng:
     * Selenium WebDriver
     *
     * Kết quả nhận được:
     * - Browser tự mở
     * - Selenium tự nhập dữ liệu
     * - Selenium tự bấm nút
     * - Test pass nếu giao diện hiển thị đúng kết quả
     */
    private final DayTimeChecker dayTimeChecker;

    public DateController(DayTimeChecker dayTimeChecker) {
        this.dayTimeChecker = dayTimeChecker;
    }

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/check")
    public String checkDate(
            @RequestParam int day,
            @RequestParam int month,
            @RequestParam int year,
            Model model
    ) {
        boolean valid = dayTimeChecker.isValidDate(day, month, year);

        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("valid", valid);

        if (valid) {
            model.addAttribute("message", "Valid date");
        } else {
            model.addAttribute("message", "Invalid date");
        }

        return "index";
    }
}