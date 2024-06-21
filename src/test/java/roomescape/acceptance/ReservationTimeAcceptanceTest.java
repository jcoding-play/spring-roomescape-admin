package roomescape.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.is;

class ReservationTimeAcceptanceTest extends AcceptanceTest {

    @Test
    @DisplayName("예약 시간을 추가한다.")
    void createReservationTime() {
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(Map.of("startAt", "10:00"))
                .when().post("/times")
                .then().log().all()
                .statusCode(201)
                .body("id", is(1));
    }

    @Test
    @DisplayName("전체 예약 시간을 조회한다.")
    void findAllReservationTimes() {
        createReservationTime();

        RestAssured.given().log().all()
                .when().get("/times")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(1));
    }

    @Test
    @DisplayName("예약 시간을 삭제한다.")
    void deleteReservationTime() {
        createReservationTime();

        RestAssured.given().log().all()
                .when().delete("/times/1")
                .then().log().all()
                .statusCode(204);
    }
}