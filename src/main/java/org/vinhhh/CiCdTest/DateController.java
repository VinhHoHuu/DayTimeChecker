package org.vinhhh.CiCdTest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DateController {

    private final DayTimeChecker dayTimeChecker;

    public DateController(DayTimeChecker dayTimeChecker) {
        this.dayTimeChecker = dayTimeChecker;
    }

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/check")
    public String checkDate(int day, int month, int year, Model model) {
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