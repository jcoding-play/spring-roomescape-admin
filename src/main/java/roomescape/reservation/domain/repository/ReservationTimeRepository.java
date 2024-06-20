package roomescape.reservation.domain.repository;

import roomescape.reservation.domain.ReservationTime;

import java.util.List;

public interface ReservationTimeRepository {

    ReservationTime save(ReservationTime reservationTime);

    List<ReservationTime> findAll();

    boolean existsById(Long id);

    void deleteById(Long id);
}
