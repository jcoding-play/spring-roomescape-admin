package roomescape.reservation.dto;

import roomescape.reservation.domain.ReservationTime;

import java.time.LocalTime;

public record ReservationTimeRequest(LocalTime startAt) {

    public ReservationTime toModel() {
        return new ReservationTime(startAt);
    }
}
