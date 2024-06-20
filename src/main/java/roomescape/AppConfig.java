package roomescape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import roomescape.reservation.domain.ReservationRepository;
import roomescape.reservation.domain.ReservationTimeRepository;
import roomescape.reservation.repository.JdbcReservationRepository;
import roomescape.reservation.repository.JdbcReservationTimeRepository;

@Configuration
public class AppConfig {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public ReservationRepository reservationRepository() {
        return new JdbcReservationRepository(jdbcTemplate);
    }

    @Bean
    public ReservationTimeRepository reservationTimeRepository() {
        return new JdbcReservationTimeRepository(jdbcTemplate);
    }
}
