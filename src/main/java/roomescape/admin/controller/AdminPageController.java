package roomescape.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/admin")
    public String adminMainPage() {
        return "admin/index";
    }

    @GetMapping("/admin/reservation")
    public String reservationPage() {
        return "admin/reservation-legacy";
    }

    @GetMapping("/admin/time")
    public String reservationTimePage() {
        return "admin/time";
    }
}
