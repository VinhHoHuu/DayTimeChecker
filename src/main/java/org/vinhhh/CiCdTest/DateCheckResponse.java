package org.vinhhh.CiCdTest;

public class DateCheckResponse {
    /*
     * API RESPONSE MODEL
     *
     * Class này đại diện cho dữ liệu JSON trả về khi test API.
     *
     * Ví dụ response:
     * {
     *   "day": 29,
     *   "month": 2,
     *   "year": 2024,
     *   "valid": true,
     *   "message": "Valid date"
     * }
     *
     * File này phục vụ cho API Testing vì Postman sẽ đọc response JSON này.
     */
    private int day;
    private int month;
    private int year;
    private boolean valid;
    private String message;

    public DateCheckResponse(int day, int month, int year, boolean valid, String message) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.valid = valid;
        this.message = message;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}