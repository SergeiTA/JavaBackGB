package HW3;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class BaseTest {
    static Properties properties = new Properties();
    static String token;
    static String username;
    static String clientID;
    static String uploadedBase64ImageDeleteHash;
    static String uploadedBase64ImageID;
    static String uploadedFileImageDeleteHash;
    static String uploadedFileImageID;

    @BeforeAll
    static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new AllureRestAssured());
        getProperties();
        token = properties.getProperty("token");
        username = properties.getProperty("username");
        clientID = properties.getProperty("clientID");
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
