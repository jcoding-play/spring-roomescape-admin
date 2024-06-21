package roomescape.fixture;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationTime;

import java.time.LocalDate;

public class ReservationFixture {

    public static Reservation PORORO_RESERVATION(ReservationTime reservationTime) {
        return new Reservation(1L, "뽀로로", LocalDate.now().plusDays(1), reservationTime);
    }
}
