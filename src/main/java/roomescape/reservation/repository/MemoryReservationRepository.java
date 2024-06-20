package roomescape.reservation.repository;

import org.springframework.stereotype.Repository;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryReservationRepository implements ReservationRepository {
    private final List<Reservation> store;

    public MemoryReservationRepository() {
        List<Reservation> reservations = initializeReservation();
        this.store = new ArrayList<>(reservations);
    }

    private List<Reservation> initializeReservation() {
        Reservation firstReservation = new Reservation(1L, "브라운", LocalDate.now(), LocalTime.now());
        Reservation secondReservation = new Reservation(2L, "브라운", LocalDate.now(), LocalTime.now());

        return List.of(firstReservation, secondReservation);
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(store);
    }
}
