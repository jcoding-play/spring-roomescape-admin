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
    public String reservationsPage() {
        return "admin/reservation-legacy";
    }
}
