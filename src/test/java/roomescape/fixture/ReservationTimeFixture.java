package roomescape.fixture;

import roomescape.reservation.domain.ReservationTime;

import java.time.LocalTime;

public class ReservationTimeFixture {

    public static ReservationTime RESERVATION_TIME() {
        return new ReservationTime(1L, LocalTime.now());
    }
}
