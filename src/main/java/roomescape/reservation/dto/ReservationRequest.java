package roomescape.reservation.dto;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationTime;

import java.time.LocalDate;

public record ReservationRequest(String name, LocalDate date, Long timeId) {

    public Reservation toModel(ReservationTime time) {
        return new Reservation(name, date, time);
    }
}
