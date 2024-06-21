package roomescape.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import roomescape.ControllerTest;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationTime;
import roomescape.reservation.dto.ReservationRequest;
import roomescape.reservation.service.ReservationService;
import roomescape.reservation.service.ReservationTimeService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static roomescape.fixture.ReservationFixture.PORORO_RESERVATION;
import static roomescape.fixture.ReservationTimeFixture.RESERVATION_TIME;

class ReservationControllerTest extends ControllerTest {

    private Reservation reservation;

    @BeforeEach
    void setUp() {
        ReservationTime reservationTime = RESERVATION_TIME();
        reservation = PORORO_RESERVATION(reservationTime);
    }

    @Test
    void findAllReservations() throws Exception {
        BDDMockito.given(reservationService.findAll())
                .willReturn(List.of(reservation));

        mockMvc.perform(get("/reservations"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("뽀로로"));
    }

    @Test
    void createReservation() throws Exception {
        ReservationRequest request = new ReservationRequest("뽀로로", LocalDate.now().plusDays(1), 1L);

        BDDMockito.given(reservationService.create(any()))
                .willReturn(reservation);

        mockMvc.perform(post("/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("뽀로로"));
    }

    @Test
    void deleteReservation() throws Exception {
        BDDMockito.willDoNothing()
                .given(reservationService).delete(anyLong());

        mockMvc.perform(delete("/reservations/{id}", anyLong()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
