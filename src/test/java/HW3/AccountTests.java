package HW3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AccountTests extends BaseTest{
    static Map<String, String> headers = new HashMap<>();

    @BeforeAll
    static void setUp() {
        headers.put("Authorization", "Bearer 9d2306f677fa45ecbbe39df15c86f710fb9692fc");
    }


    @Test
    void getAccountInfoTest() {
        given()
                .headers(headers)
        .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek()
        .then()
                .statusCode(200);
    }

    @Test
    void getAccountInfoVerifyUrlInGivenPartTest() {
        given()
                .headers(headers)
                .log().method()
                .log().uri()
        .when()
                .get("https://api.imgur.com/3/account/{username}", username)
        .then()
                .statusCode(200)
                .contentType("application/json")
                .assertThat()
                    .body("success", equalTo(true))
                    .body("data.url", equalTo(username));
    }


    @Test
    void getAccountInfoWithAssertionsGivenTest() {
        given()
                .headers(headers)
                .log().method()
                .log().uri()
                .expect()
                    .statusCode(200)
                    .body("success", equalTo(true))
                    .body("data.url", equalTo(username))
                    .body("status", equalTo(200))
                    .contentType("application/json")
        .when()
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek();
    }


}
