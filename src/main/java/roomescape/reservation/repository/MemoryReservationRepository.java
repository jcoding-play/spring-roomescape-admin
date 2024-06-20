package roomescape.reservation.repository;

import org.springframework.stereotype.Repository;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryReservationRepository implements ReservationRepository {
    private static final int INITIAL_VALUE_OF_RESERVATION_ID = 1;

    private final List<Reservation> store;
    private final AtomicLong idGenerator;

    public MemoryReservationRepository() {
        this.store = new ArrayList<>();
        this.idGenerator = new AtomicLong(INITIAL_VALUE_OF_RESERVATION_ID);
    }

    @Override
    public Reservation save(Reservation reservation) {
        String name = reservation.getName();
        LocalDate date = reservation.getDate();
        LocalTime time = reservation.getTime();
        Reservation savedReservation = new Reservation(idGenerator.getAndIncrement(), name, date, time);

        store.add(savedReservation);
        return savedReservation;
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(store);
    }
}
