package roomescape.reservation.service;

import org.springframework.stereotype.Service;
import roomescape.reservation.domain.ReservationTime;
import roomescape.reservation.domain.repository.ReservationTimeRepository;

import java.util.List;

@Service
public class ReservationTimeService {
    private final ReservationTimeRepository reservationTimeRepository;

    public ReservationTimeService(ReservationTimeRepository reservationTimeRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
    }

    public ReservationTime create(ReservationTime reservationTime) {
        return reservationTimeRepository.save(reservationTime);
    }

    public List<ReservationTime> findAll() {
        return reservationTimeRepository.findAll();
    }

    public void delete(Long id) {
        if (!reservationTimeRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("입력된 Id가 존재하지 않아 예약 시간을 삭제할 수 없습니다. 입력된 id=%d", id));
        }
        reservationTimeRepository.deleteById(id);
    }
}
