package homeWorks;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.util.Properties;

import static org.hamcrest.Matchers.is;

public abstract class BaseTest {
    static Properties properties = new Properties();
    static ResponseSpecification responseSpecificationStatusOK, responseSpecification404, responseSpecification403;
    static RequestSpecification requestSpecificationToken, requestSpecificationClientID;
    static String token;
    static String username;
    static String clientID;
    static String externalImgURL;
    static String uploadedBase64ImageDeleteHash;
    static String uploadedBase64ImageID;
    static String uploadedFileImageDeleteHash;
    static String uploadedFileImageID;


    @BeforeAll
    static void beforeAll() {
        getProperties();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
        RestAssured.baseURI = properties.getProperty("baseURL");
        RestAssured.basePath = properties.getProperty("basePath");

        token = properties.getProperty("token");
        username = properties.getProperty("username");
        clientID = properties.getProperty("clientID");
        externalImgURL = properties.getProperty("externalImgURL");



        responseSpecificationStatusOK = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThanOrEqualTo(5000L))
                .expectBody("success", is(true))
                .expectBody("status", is(200))
                .build();

        responseSpecification404 = new ResponseSpecBuilder()
                .expectStatusCode(404)
                .expectContentType(ContentType.HTML)
                .expectStatusLine("HTTP/1.1 404 Not Found")
                .expectResponseTime(Matchers.lessThanOrEqualTo(5000L))
                .build();

        responseSpecification403 = new ResponseSpecBuilder()
                .expectStatusCode(403)
                .expectStatusLine("HTTP/1.1 403 Permission Denied")
                .expectContentType(ContentType.JSON)
                .expectBody("success", is(false))
                .expectBody("status", is(403))
                .expectResponseTime(Matchers.lessThanOrEqualTo(5000L))
                .build();

        requestSpecificationToken = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addHeader("Connection", "keep-alive")
                .setAccept(ContentType.ANY)//В коллекции постмана указано такое значение для заголовка "Accept"
                .build();


        requestSpecificationClientID = new RequestSpecBuilder()
                .addHeader("Authorization", clientID)
                .addHeader("Connection", "keep-alive")
                .setAccept(ContentType.ANY)//В коллекции постмана указано такое значение для заголовка "Accept"
                .build();

    }

    //По какой то причине getProperty не подхватывает хначение из @BeforeAll
    // , если запись происходит из десериализованного класса. Со строкой из extract->jsonpath такой пробемы нет
    //Пришлось вынести часть кода в @BeforeEach
    //Если выполнять в чистом контекте юнит атомарных .нит тестов такой проблемы не будет по определею
    // , так как не будет надобности в таком харантере использования property
    //Если использовать фреймворк BDD, то можно будет изолировать инстанс рана тестов и юзать Map<String, Object>
    // для хранения данных между тестами внутри выделенных сценариев
    @BeforeEach
    void beforeEach() {
        uploadedBase64ImageDeleteHash = properties.getProperty("uploadedBase64ImageDeleteHash");
        uploadedBase64ImageID = properties.getProperty("uploadedBase64ImageID");
        uploadedFileImageDeleteHash = properties.getProperty("uploadedFileImageDeleteHash");
        uploadedFileImageID = properties.getProperty("uploadedFileImageID");
    }


    private static void getProperties() {
        try {
            InputStream output = new FileInputStream("src/test/resources/application.properties");
            properties.load(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
