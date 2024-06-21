package roomescape.reservation.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.http.MediaType;
import roomescape.utils.ControllerTest;
import roomescape.reservation.domain.ReservationTime;
import roomescape.reservation.dto.ReservationTimeRequest;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static roomescape.fixture.ReservationTimeFixture.RESERVATION_TIME;

class ReservationTimeControllerTest extends ControllerTest {

    private ReservationTime reservationTime;

    @BeforeEach
    void setUp() {
        reservationTime = RESERVATION_TIME();
    }

    @Test
    void findAllReservationTimes() throws Exception {
        BDDMockito.given(reservationTimeService.findAll())
                .willReturn(List.of(reservationTime));

        mockMvc.perform(get("/times"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void createReservationTime() throws Exception {
        ReservationTimeRequest request = new ReservationTimeRequest(LocalTime.now());

        BDDMockito.given(reservationTimeService.create(any()))
                .willReturn(reservationTime);

        mockMvc.perform(post("/times")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deleteReservationTime() throws Exception {
        BDDMockito.willDoNothing()
                .given(reservationTimeService)
                .delete(anyLong());

        mockMvc.perform(delete("/times/{id}", anyLong()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
