package roomescape.reservation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationTime;
import roomescape.reservation.dto.ReservationRequest;
import roomescape.reservation.dto.ReservationResponse;
import roomescape.reservation.service.ReservationService;
import roomescape.reservation.service.ReservationTimeService;

import java.net.URI;
import java.util.List;

@RestController
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationTimeService reservationTimeService;

    public ReservationController(ReservationService reservationService, ReservationTimeService reservationTimeService) {
        this.reservationService = reservationService;
        this.reservationTimeService = reservationTimeService;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponse>> findAllReservations() {
        List<Reservation> reservations = reservationService.findAll();

        return ResponseEntity.ok(reservations.stream()
                .map(ReservationResponse::from)
                .toList());
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest request) {
        ReservationTime reservationTime = reservationTimeService.findById(request.timeId());
        Reservation reservation = reservationService.create(request.toModel(reservationTime));

        return ResponseEntity.created(URI.create("/reservations/" + reservation.getId()))
                .body(ReservationResponse.from(reservation));
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservationService.delete(id);

        return ResponseEntity.noContent()
                .build();
    }
}
