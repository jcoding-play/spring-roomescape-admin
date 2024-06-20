package roomescape.reservation.repository;

import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationTime;
import roomescape.reservation.domain.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryReservationRepository implements ReservationRepository {
    private static final int INITIAL_VALUE_OF_RESERVATION_ID = 1;

    private final Map<Long, Reservation> store;
    private final AtomicLong idGenerator;

    public MemoryReservationRepository() {
        this.store = new ConcurrentHashMap<>();
        this.idGenerator = new AtomicLong(INITIAL_VALUE_OF_RESERVATION_ID);
    }

    @Override
    public Reservation save(Reservation reservation) {
        long id = idGenerator.getAndIncrement();
        String name = reservation.getName();
        LocalDate date = reservation.getDate();
        ReservationTime time = reservation.getTime();
        Reservation savedReservation = new Reservation(id, name, date, time);

        store.put(id, savedReservation);
        return savedReservation;
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean existsById(long id) {
        return store.containsKey(id);
    }

    @Override
    public void deleteById(long id) {
        store.remove(id);
    }
}
