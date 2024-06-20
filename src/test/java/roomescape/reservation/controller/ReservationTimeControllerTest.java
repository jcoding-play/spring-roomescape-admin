package roomescape.reservation.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roomescape.AcceptanceTest;

import java.util.Map;

import static org.hamcrest.Matchers.is;

class ReservationTimeControllerTest extends AcceptanceTest {

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
}
