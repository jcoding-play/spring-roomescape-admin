package roomescape.admin.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import roomescape.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AdminPageControllerTest extends ControllerTest {

    @Test
    @DisplayName("어드민 메인 페이지를 조회한다.")
    void adminMainPage() throws Exception {
        mockMvc.perform(get("/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약 페이지를 조회한다.")
    void reservationPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/reservation"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("예약 시간 페이지를 조회한다.")
    void reservationTimePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/time"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
