package roomescape.reservation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import roomescape.reservation.domain.Reservation;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static roomescape.fixture.ReservationFixture.PORORO_RESERVATION;
import static roomescape.fixture.ReservationTimeFixture.RESERVATION_TIME;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/test-schema.sql")
class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationTimeService reservationTimeService;

    @Test
    void findAll() {
        reservationTimeService.create(RESERVATION_TIME());
        reservationService.create(PORORO_RESERVATION(RESERVATION_TIME()));

        List<Reservation> reservations = reservationService.findAll();

        assertThat(reservations).hasSize(1);
    }

    @Test
    void create() {
        reservationTimeService.create(RESERVATION_TIME());

        Reservation reservation = reservationService.create(PORORO_RESERVATION(RESERVATION_TIME()));

        assertThat(reservation).isNotNull();
    }

    @Test
    void delete() {
        reservationTimeService.create(RESERVATION_TIME());
        Reservation reservation = reservationService.create(PORORO_RESERVATION(RESERVATION_TIME()));

        reservationService.delete(reservation.getId());
        List<Reservation> reservations = reservationService.findAll();

        assertThat(reservations).hasSize(0);
    }
}
