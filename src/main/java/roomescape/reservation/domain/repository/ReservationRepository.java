package roomescape.reservation.domain.repository;

import roomescape.reservation.domain.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation save(Reservation reservation);

    List<Reservation> findAll();

    boolean existsById(long id);

    void deleteById(long id);
}
