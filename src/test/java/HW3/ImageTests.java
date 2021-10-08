package HW3;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageTests extends BaseTest {
    private final String PATH_TO_IMAGE = "src/test/resources/MY4R4.png";
    static String encodedFile;
    String imageTitleBase64 = "ImageTitleBase64";
    String imageTitleFile = "ImageTitleFile";

    //@BeforeEach
//    void beforeTest() {
//        byte[] byteArray = getFileContent();
//        encodedFile = Base64.getEncoder().encodeToString(byteArray);
//    }


    @Test
    @Order(1)
    void uploadImageBase64Test() {
        encodedFile = Base64.getEncoder().encodeToString(getFileContent());
        uploadedBase64ImageID = given()
                .header("Authorization", token)
                .multiPart("image", encodedFile)
                .formParam("title", imageTitleBase64)
                .expect()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("data.id", is(notNullValue()))
                .when()
                    .post("https://api.imgur.com/3/image")
                    .prettyPeek()
                .then()
                    .extract().response().jsonPath().getString("data.id");

        properties.setProperty("uploadedBase64ImageID", uploadedBase64ImageID);

    }

    @Test
    @Order(2)
    void uploadFileImageTest() {
        uploadedFileImageID = given()
                .header("Authorization", token)
                .multiPart("image", new File(PATH_TO_IMAGE))
                .formParam("title", imageTitleFile)
                .expect()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("data.id", is(notNullValue()))
                .when()
                    .post("https://api.imgur.com/3/image")
                    .prettyPeek()
                .then()
                    .extract().response().jsonPath().getString("data.id");

        properties.setProperty("uploadedFileImageID", uploadedFileImageID);
    }


    @Test
    @Order(3)
    void findImageByIdTest() {
        uploadedBase64ImageDeleteHash = given()
                .header("Authorization", token)
                .expect()
                    .statusCode(200)
                    .body("data.title", is(imageTitleBase64))
                    .body("success", is(true))
                    .body("data.id", is(notNullValue()))
                    .body("data.link", is("https://i.imgur.com/" + uploadedBase64ImageID + ".png"))
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .extract() .response().jsonPath().getString("data.deletehash");

        properties.setProperty("uploadedBase64ImageDeleteHash", uploadedBase64ImageDeleteHash);

        uploadedFileImageDeleteHash = given()
                .header("Authorization", token)
                .expect()
                    .statusCode(200)
                    .body("data.title", is(imageTitleFile))
                    .body("success", is(true))
                    .body("data.id", is(notNullValue()))
                    .body("data.link", is("https://i.imgur.com/" + uploadedFileImageID + ".png"))
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .extract().response().jsonPath().getString("data.deletehash");

        properties.setProperty("uploadedFileImageDeleteHash", uploadedFileImageDeleteHash);
    }


    @Test
    @Order(4)
    void updateImageInfoUNAuthTest() {
        given()
                .header("Authorization", clientID)
                .formParam("title", imageTitleBase64 + " NEW")
                .formParam("description", imageTitleBase64 + " description")
                //.body("success", is(true))
                //.body("data.id", is(notNullValue()))
                .when()
                    .post("https://api.imgur.com/3/image/{imageDeleteHash}", uploadedBase64ImageDeleteHash)
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data", is(true))
                    .body("success", is(true))
                    .body("status", is(200));
    }


    @Test
    @Order(5)
    void updateImageInfoAuthTest() {
        given()
                .header("Authorization", token)
                .formParam("title", imageTitleFile + " NEW")
                .formParam("description", imageTitleFile + " description")
                //.body("success", is(true))
                //.body("data.id", is(notNullValue()))
                .when()
                    .post("https://api.imgur.com/3/image/{imageDeleteHash}", uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data", is(true))
                    .body("success", is(true))
                    .body("status", is(200));
    }


    @Test
    @Order(6)
    void titleImageUNAuthHasBeenUpdatedTest() {
        given()
                .header("Authorization", token)
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, uploadedBase64ImageDeleteHash)
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data.title", is(imageTitleBase64 + " NEW"))
                    .body("data.description", is(imageTitleBase64 + " description"))
                    .body("success", is(true))
                    .body("data.id", is(notNullValue()))
                    .body("data.link", is("https://i.imgur.com/" + uploadedBase64ImageID + ".png"));
    }


    @Test
    @Order(7)
    void titleImageAuthHasBeenUpdatedTest() {
        given()
                .header("Authorization", token)
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data.title", is(imageTitleFile + " NEW"))
                    .body("data.description", is(imageTitleFile + " description"))
                    .body("success", is(true))
                    .body("data.id", is(notNullValue()))
                    .body("data.link", is("https://i.imgur.com/" + uploadedFileImageID + ".png"));
    }


    @Test
    @Order(8)
    void updateImageInfoWhitInvalidTokenTest() {
        given()
                .header("Authorization", "A1" + token) ///Вот тут интересно очему с искаженным токеном в начале строки нам разрешают доступ
                .formParam("title", imageTitleFile + " NEW")
                .formParam("description", imageTitleFile + " description")
                .when()
                    .post("https://api.imgur.com/3/image/{imageDeleteHash}", uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .statusCode(403)
                    .body("data.error", is("The access token provided is invalid."))
                    .body("data.request", is("/3/account/testprogmath/image/" + uploadedFileImageID))
                    .body("success", is(false));
    }



    @Test
    @Order(9)
    void updateImageInfoWithClientIDAndImageIDTest() {
        given()
                .header("Authorization", clientID)
                .formParam("title", imageTitleFile + " NEW2")
                .formParam("description", imageTitleFile + " description2")
                .when()
                    .post("https://api.imgur.com/3/image/{imageDeleteHash}", uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .statusCode(403)
                    .body("data.error", is("Permission denied"))
                    .body("data.request", is("/3/account/testprogmath/image/" + uploadedBase64ImageID))
                    .body("success", is(false));
    }



    @Test
    @Order(10)
    void deleteImageUNAuthTest() {
        given()
                .header("Authorization", clientID)
                .when()
                    .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username, uploadedBase64ImageDeleteHash)
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data", is(true))
                    .body("success", is(true))
                    .body("status", is(200));
    }


    @Test
    @Order(11)
    void deleteImageAuthTest() {
        given()
                .header("Authorization", token)
                .when()
                    .delete("https://api.imgur.com/3/account/{username}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data", is(true))
                    .body("success", is(true))
                    .body("status", is(200));
    }

    @Test
    @Order(12)
    void findImageAfterDeletingWithUNAuthTest() {
        given()
                .header("Authorization", token)
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data.error", is("Unable to find an image with the id, " + uploadedBase64ImageID))
                    .body("data.request", is("/3/account/testprogmath/image/" + uploadedBase64ImageID));
    }


    @Test
    @Order(13)
    void findImageAfterDeletingWithAuthTest() {
        given()
                .header("Authorization", token)
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data.error", is("Unable to find an image with the id, " + uploadedFileImageID))
                    .body("data.request", is("/3/account/testprogmath/image/" + uploadedFileImageID));
    }


    @Test
    @Order(14)
    void deleteUnExistingFilesTest() {
        given()
                .header("Authorization", token)
                .log().uri()
                .when()
                    .delete("https://api.imgur.com/3/account/{username}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .statusCode(200); ///Странно, но вот так себя ведет API, по хорошему тут можно поставить ожидание 404 и это будет баг

        given()
                .header("Authorization", token)
                    .log().uri()
                .when()
                    .delete("https://api.imgur.com/3/account/{username}/image/{imageID}", username, uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .statusCode(200); ///Странно, но вот так себя ведет API, по хорошему тут можно поставить ожидание 404 и это будет баг
    }


    @Test
    @Order(15)
    void findImagesWithoutAutTokenTest() {
        given()
                .header("Authorization", "")
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .statusCode(403)
                    .body("data.error", is("The access token was not found."))
                    .body("data.request", is("/3/account/testprogmath/image/" + uploadedFileImageID))
                    .body("success", is(false));
    }


    @Test
    @Order(16)
    void findImagesWithoutImageIDTest() {
        given()
                .header("Authorization", token)
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, "")
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data.error", is("An image ID is required for a GET request to /image"))
                    .body("data.request", is("/3/account/testprogmath/image/"))
                    .body("success", is(true));
    }

    @Test
    @Order(17)
    void findImagesWithInvalidImageIDTest() {
        given()
                .header("Authorization", token)
                .when()
                    .get("https://api.imgur.com/3/account/{userName}/image/{imageID}", username, "+aa(*^^^%$#^*(")
                    .prettyPeek()
                .then()
                    .statusCode(200)
                    .body("data.error", is("An image ID is required for a GET request to /image"))
                    .body("data.request", is("/3/account/testprogmath/image/" + "+aa(*^^^%$#^*("))
                    .body("success", is(true));
    }


    @Test
    @Order(18)
    void uploadImageBase64WithoutTokenTest() {
        encodedFile = Base64.getEncoder().encodeToString(getFileContent());
        given()
                .header("Authorization", "")
                .multiPart("image", encodedFile)
                .formParam("title", imageTitleBase64)
                .when()
                    .post("https://api.imgur.com/3/image")
                    .prettyPeek()
                .then()
                    .statusCode(403)
                    .body("data.error", is("The access token was not found."))
                    .body("data.request", is("/3/image"))
                    .body("success", is(false));
    }

    @Test
    @Order(19)
    void uploadImageBase64WithInvalidURLTest() {
        encodedFile = Base64.getEncoder().encodeToString(getFileContent());
        given()
                .header("Authorization", token)
                .multiPart("image", encodedFile)
                .formParam("title", imageTitleBase64)
                .when()
                    .post("https://api.imgur.com/(^%^#$%#%&&*((*)/image")
                    .prettyPeek()
                .then()
                    .statusCode(404)
                    .contentType("text/html; charset=utf-8");
    }


    @Test
    @Order(20)
    void deleteImageWithInvalidTokenTest() {
        given()
                .header("Authorization", token + "A!")
                .when()
                    .delete("https://api.imgur.com/3/account/{username}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .statusCode(403)
                    .body("data.error", is("The access token provided is invalid."))
                    .body("data.request", is("/3/account/testprogmath/image/" + uploadedFileImageID))
                    .body("success", is(false));
    }







    private byte[] getFileContent() {
        byte[] byteArray = new byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(PATH_TO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }


}
