package roomescape.reservation.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import roomescape.reservation.domain.Reservation;
import roomescape.reservation.domain.ReservationTime;
import roomescape.reservation.domain.repository.ReservationRepository;

import java.util.List;

public class JdbcReservationRepository implements ReservationRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcReservationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("reservation")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Reservation save(Reservation reservation) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", reservation.getName())
                .addValue("date", reservation.getDate())
                .addValue("time_id", reservation.getTime().getId());
        long id = jdbcInsert.executeAndReturnKey(params).longValue();

        return new Reservation(id, reservation.getName(), reservation.getDate(), reservation.getTime());
    }

    @Override
    public List<Reservation> findAll() {
        String sql = "SELECT " +
                "r.id as reservation_id, " +
                "r.name, " +
                "r.date, " +
                "t.id as time_id, " +
                "t.start_at as time_value " +
                "FROM reservation as r " +
                "INNER JOIN reservation_time as t " +
                "ON r.time_id = t.id";

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Reservation(
                resultSet.getLong("reservation_id"),
                resultSet.getString("name"),
                resultSet.getDate("date").toLocalDate(),
                new ReservationTime(
                        resultSet.getLong("time_id"),
                        resultSet.getTime("time_value").toLocalTime()
                )
        ));
    }

    @Override
    public boolean existsById(long id) {
        String sql = "SELECT EXISTS (SELECT 1 FROM reservation WHERE id = ?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id);
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
