package homeWorks;

import homeWorks.models.ResponseAccountInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AccountTests extends BaseTest{

    @Test
    void getAccountInfoTest() {
        given()
                .spec(requestSpecificationToken)
        .when()
                .get("/account/{username}", username)
                .prettyPeek()
        .then()
                .spec(responseSpecificationStatusOK);
    }

    @Test
    void getAccountInfoVerifyUrlInGivenPartTest() {
        given()
                .spec(requestSpecificationToken)
                .log().method()
                .log().uri()
        .when()
                .get("/account/{username}", username)
        .then()
                .spec(responseSpecificationStatusOK)
                .assertThat()
                    .body("data.url", equalTo(username));
    }


    @Test
    void getAccountInfoWithAssertionsGivenTest() {
        given()
                .spec(requestSpecificationToken)
                .log().method()
                .log().uri()
                .expect()
                    .spec(responseSpecificationStatusOK)
                    .body("data.url", equalTo(username))
        .when()
                .get("/account/{username}", username)
                .prettyPeek();
    }



    @Test
    void getAccountInfoTestWithResponseClass() {
        ResponseAccountInfo responseAccountInfo =
                given()
                        .spec(requestSpecificationToken)
                        .log().method()
                        .log().uri()
                .when()
                        .get("/account/{username}", username)
                        .prettyPeek()
                .then()
                    .extract().body().as(ResponseAccountInfo.class);


        System.out.println(responseAccountInfo.toString());
        //Теперь можно сравнивать уже поля классов, ращаться с ответом как объектом более гибко
        //Если бы прокинули наследование ResponseBase класса от Response, можно было бы десериализовывать поля статуса,
        // кода ответа, заголовков и пр. Но на это нужно немного больше времени
        assertThat(responseAccountInfo.getData().getUrl(), equalTo(username));
    }



}
