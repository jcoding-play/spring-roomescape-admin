package roomescape.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import roomescape.reservation.service.ReservationTimeService;

import java.util.Map;

import static org.hamcrest.Matchers.is;
import static roomescape.fixture.ReservationTimeFixture.RESERVATION_TIME;

class ReservationAcceptanceTest extends AcceptanceTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ReservationTimeService reservationTimeService;

    @Test
    @DisplayName("전체 예약을 조회한다.")
    void findAllReservations() {
        RestAssured.given().log().all()
                .when().get("/reservations")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(0));
    }

    @Test
    @DisplayName("예약을 추가한다.")
    void createReservation() {
        reservationTimeService.create(RESERVATION_TIME());

        Map<String, String> params = Map.of(
                "name", "브라운",
                "date", "2024-08-05",
                "timeId", "1");

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(params)
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201)
                .body("id", is(1));
    }

    @Test
    @DisplayName("예약을 삭제한다.")
    void deleteReservation() {
        createReservation();

        RestAssured.given().log().all()
                .when().delete("/reservations/1")
                .then().log().all()
                .statusCode(204);
    }
}
