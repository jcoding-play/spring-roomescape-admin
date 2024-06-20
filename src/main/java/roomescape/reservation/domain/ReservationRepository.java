package roomescape.reservation.domain;

import java.util.List;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    List<Reservation> findAll();

    boolean existsById(long id);

    void deleteById(long id);
}
