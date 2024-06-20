package roomescape.reservation.service;

import org.springframework.stereotype.Service;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.repository.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation create(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void delete(long id) {
        if (!reservationRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("입력된 Id가 존재하지 않아 예약을 삭제할 수 없습니다. 입력된 id=%d", id));
        }
        reservationRepository.deleteById(id);
    }
}
