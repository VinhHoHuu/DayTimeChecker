import http from 'k6/http';
import { check, sleep } from 'k6';

/*
 * PERFORMANCE TESTING WITH k6
 *
 * Bản chất:
 * Performance Testing kiểm tra hệ thống phản hồi nhanh hay chậm
 * khi có nhiều request hoặc nhiều virtual users truy cập cùng lúc.
 *
 * Tool dùng:
 * k6
 *
 * API được test:
 * GET /api/check-date
 *
 * Cách chạy:
 * 1. Chạy Spring Boot app trước:
 *    mvn spring-boot:run
 *
 * 2. Mở terminal khác tại folder k6-tests, chạy:
 *    k6 run date-api-performance-test.js
 *
 * Hoặc nếu đang đứng ở project root, chạy:
 *    k6 run k6-tests/date-api-performance-test.js
 */

export const options = {
    vus: 100,
    duration: '30s',

    thresholds: {
        http_req_failed: ['rate<0.01'],
        http_req_duration: ['p(95)<500'],
    },
};

export default function () {
    const url = 'http://localhost:8080/api/check-date?day=29&month=2&year=2024';

    const res = http.get(url);

    check(res, {
        'status is 200': function (r) {
            return r.status === 200;
        },
        'response valid is true': function (r) {
            return r.json('valid') === true;
        },
        'response message is Valid date': function (r) {
            return r.json('message') === 'Valid date';
        },
    });

    sleep(1);
}