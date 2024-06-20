package roomescape.reservation.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import roomescape.reservation.domain.ReservationTime;
import roomescape.reservation.domain.repository.ReservationTimeRepository;

import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;

public class JdbcReservationTimeRepository implements ReservationTimeRepository {
    private static final RowMapper<ReservationTime> RESERVATION_TIME_ROW_MAPPER = (resultSet, rowNum) -> new ReservationTime(
            resultSet.getLong("id"),
            resultSet.getTime("start_at").toLocalTime()
    );
    private final JdbcTemplate jdbcTemplate;

    public JdbcReservationTimeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ReservationTime save(ReservationTime reservationTime) {
        String sql = "INSERT INTO reservation_time (start_at) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setTime(1, Time.valueOf(reservationTime.getStartAt()));
            return ps;
        }, keyHolder);

        long id = keyHolder.getKey().longValue();
        return new ReservationTime(id, reservationTime.getStartAt());
    }

    @Override
    public List<ReservationTime> findAll() {
        String sql = "SELECT * FROM reservation_time";
        return jdbcTemplate.query(sql, RESERVATION_TIME_ROW_MAPPER);
    }

    @Override
    public ReservationTime findById(Long id) {
        String sql = "SELECT * FROM reservation_time WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, RESERVATION_TIME_ROW_MAPPER, id);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM reservation_time WHERE id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM reservation_time WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
