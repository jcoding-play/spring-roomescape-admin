package roomescape.reservation.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import roomescape.AcceptanceTest;

import static org.hamcrest.Matchers.is;

class ReservationControllerTest extends AcceptanceTest {

    @Test
    void findAllReservations() {
        RestAssured.given().log().all()
                .when().get("/reservations")
                .then().log().all()
                .statusCode(200)
                .body("size()", is(2));
    }
}
