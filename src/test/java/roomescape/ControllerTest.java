package roomescape;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import roomescape.reservation.service.ReservationService;
import roomescape.reservation.service.ReservationTimeService;

@WebMvcTest
public abstract class ControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public ReservationService reservationService;

    @MockBean
    public ReservationTimeService reservationTimeService;
}
