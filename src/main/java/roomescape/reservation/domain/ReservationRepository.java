package roomescape.reservation.domain;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findAll();
}
