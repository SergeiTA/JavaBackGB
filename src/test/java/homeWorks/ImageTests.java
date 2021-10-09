package homeWorks;

import homeWorks.models.ResponseImage;
import homeWorks.models.ResponseInform;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImageTests extends BaseTest {
    private final String PATH_TO_IMAGE = "src/test/resources/MY4R4.png";
    static String encodedFile;
    String imageTitleBase64 = "ImageTitleBase64";
    String imageTitleFile = "ImageTitleFile";

    //Использовать MultiPartSpecBuilder в этом конкретном классе пока не вижу смысла, из-за изменений тестируемых
    // заголовков количество строк кода не сократиться, а более тяжелая логика замедлит выполнение тестов.
    //В другом случае, когда нужно будет переиспользовать код и это сократит затраты на обработку
    // и поддержку кода - использование будет иметь смысл

    @Test
    @Order(1)
    void uploadImageBase64Test() {
        encodedFile = Base64.getEncoder().encodeToString(getFileContent());
        ///Получаем объет класса из ответа
        ResponseImage uploadedBase64ImageID = given()
                //В коллекции Postman загрузка файлов идет с ClientID, по хорошему - тут либо баг
                // , либо ошибка документации
                .spec(requestSpecificationToken)
                .multiPart("image", encodedFile)
                .formParam("title", imageTitleBase64)
                .expect()
                    .spec(responseSpecificationStatusOK)
                    .body("data.id", is(notNullValue()))
                .when()
                    .post("/image")
                    .prettyPeek()
                .then()
                    .extract().as(ResponseImage.class);

        properties.setProperty("uploadedBase64ImageID", uploadedBase64ImageID.getData().getId());


    }

    @Test
    @Order(2)
    void uploadFileImageTest() {
        ///Получаем объет класса из ответа
        ResponseImage uploadedFileImageID = given()
                .spec(requestSpecificationToken)
                .multiPart("image", new File(PATH_TO_IMAGE))
                .formParam("title", imageTitleFile)
                .expect()
                    .spec(responseSpecificationStatusOK)
                    .body("data.id", is(notNullValue()))
                .when()
                    .post("/image")
                    .prettyPeek()
                .then()
                    .extract().as(ResponseImage.class);

        properties.setProperty("uploadedFileImageID", uploadedFileImageID.getData().getId());
    }


    @Test
    @Order(3)
    void makeUploadedImageFavorite() {
        System.out.println("-----------------");
        System.out.println(properties.getProperty("uploadedBase64ImageID"));

        given()
                .spec(requestSpecificationToken)
                .log().uri()
                .when()
                    .post("/image/{imageID}/favorite", uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .spec(responseSpecificationStatusOK)
                    .body("data", is("favorited"));
    }


    @Test
    @Order(4)
    void findImageByIdTest() {
        uploadedBase64ImageDeleteHash = given()
                .spec(requestSpecificationToken)
                .expect()
                    .spec(responseSpecificationStatusOK)
                    .body("data.title", is(imageTitleBase64))
                    .body("data.id", is(notNullValue()))
                    .body("data.link", is(externalImgURL + uploadedBase64ImageID + ".png"))
                    .body("data.favorite", is(true))
                .when()
                    .get("/account/{userName}/image/{imageID}", username, uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .extract() .response().jsonPath().getString("data.deletehash");

        properties.setProperty("uploadedBase64ImageDeleteHash", uploadedBase64ImageID);

        uploadedFileImageDeleteHash = given()
                .spec(requestSpecificationToken)
                .expect()
                    .spec(responseSpecificationStatusOK)
                    .body("data.title", is(imageTitleFile))
                    .body("data.id", is(notNullValue()))
                    .body("data.link", is(externalImgURL + uploadedFileImageID + ".png"))
                    .body("data.favorite", is(false))
                .when()
                    .get("/account/{userName}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .extract().response().jsonPath().getString("data.deletehash");

        properties.setProperty("uploadedFileImageDeleteHash", uploadedFileImageDeleteHash);
    }


    @Test
    @Order(5)
    void updateImageInfoUNAuthTest() {
        System.out.println(clientID);
        given()
                .spec(requestSpecificationClientID)
                .log().headers()
                .log().uri()
                .formParam("title", imageTitleBase64 + " NEW")
                .formParam("description", imageTitleBase64 + " description")
                .when()
                    .post("/image/{imageDeleteHash}", uploadedBase64ImageDeleteHash)
                    .prettyPeek()
                .then()
                    .spec(responseSpecificationStatusOK)
                    .body("data", is(true));

    }


    @Test
    @Order(6)
    void updateImageInfoAuthTest() {
        given()
                .spec(requestSpecificationToken)
                .formParam("title", imageTitleFile + " NEW")
                .formParam("description", imageTitleFile + " description")
                .when()
                    .post("/image/{imageDeleteHash}", uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .spec(responseSpecificationStatusOK)
                    .body("data", is(true));
    }


    @Test
    @Order(7)
    void titleImageUNAuthHasBeenUpdatedTest() {
        given()
                .spec(requestSpecificationToken)
                .when()
                    .get("/account/{userName}/image/{imageID}", username, uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .spec(responseSpecificationStatusOK)
                    .body("data.title", is(imageTitleBase64 + " NEW"))
                    .body("data.description", is(imageTitleBase64 + " description"))
                    .body("data.id", is(notNullValue()))
                    .body("data.link", is(externalImgURL + uploadedBase64ImageID + ".png"));
    }


    @Test
    @Order(8)
    void titleImageAuthHasBeenUpdatedTest() {
        given()
                .spec(requestSpecificationToken)
                .when()
                    .get("/account/{userName}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .spec(responseSpecificationStatusOK)
                    .body("data.title", is(imageTitleFile + " NEW"))
                    .body("data.description", is(imageTitleFile + " description"))
                    .body("data.id", is(notNullValue()))
                    .body("data.link", is(externalImgURL + uploadedFileImageID + ".png"));
    }


    @Test
    @Order(9)
    void updateImageInfoWhitInvalidTokenTest() {
        ResponseInform responseInform = given()
                .header("Authorization", "A1" + token) ///Вот тут интересно очему с искаженным токеном в начале строки нам разрешают доступ
                .formParam("title", imageTitleFile + " NEW")
                .formParam("description", imageTitleFile + " description")
                .expect()
                    .spec(responseSpecification403)
                .when()
                    .post("/image/{imageDeleteHash}", uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);

        assertThat(responseInform.getData().getError(), equalTo("The access token provided is invalid."));

        assertThat(responseInform.getData().getRequest(), equalTo("/3/account/testprogmath/image/"
                + uploadedFileImageID));
    }



    @Test
    @Order(10)
    void updateImageInfoWithClientIDAndImageIDTest() {
        ResponseInform responseInform = given()
                .spec(requestSpecificationClientID)
                .formParam("title", imageTitleFile + " NEW2")
                .formParam("description", imageTitleFile + " description2")
                .expect()
                    .spec(responseSpecification403)
                .when()
                    .post("/image/{imageDeleteHash}", uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);

        assertThat(responseInform.getData().getError(), equalTo("Permission denied"));

        assertThat(responseInform.getData().getRequest(), equalTo("/3/image/" + uploadedFileImageID));
    }



    @Test
    @Order(11)
    void deleteImageUNAuthTest() {
        given()
                .spec(requestSpecificationClientID)
                .when()
                    .delete("/account/{username}/image/{deleteHash}", username, uploadedBase64ImageDeleteHash)
                    .prettyPeek()
                .then()
                    .spec(responseSpecificationStatusOK)
                    .body("data", is(true));
    }


    @Test
    @Order(12)
    void deleteImageAuthTest() {
        given()
                .spec(requestSpecificationToken)
                .when()
                    .delete("/account/{username}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .spec(responseSpecificationStatusOK)
                    .body("data", is(true));
    }

    @Test
    @Order(13)
    void findImageAfterDeletingWithUNAuthTest() {
        ResponseInform responseInform = given()
                .spec(requestSpecificationToken)
                .expect()
                    .spec(responseSpecificationStatusOK)
                .when()
                    .get("/account/{userName}/image/{imageID}", username, uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);

        assertThat(responseInform.getData().getError()
                , equalTo("Unable to find an image with the id, " + uploadedBase64ImageID));

        assertThat(responseInform.getData().getRequest()
                , equalTo("/3/account/testprogmath/image/" + uploadedBase64ImageID));

    }


    @Test
    @Order(14)
    void findImageAfterDeletingWithAuthTest() {
        ResponseInform responseInform = given()
                .spec(requestSpecificationToken)
                .expect()
                    .spec(responseSpecificationStatusOK)
                .when()
                    .get("/account/{userName}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);

        assertThat(responseInform.getData().getError()
                , equalTo("Unable to find an image with the id, " + uploadedFileImageID));

        assertThat(responseInform.getData().getRequest()
                , equalTo("/3/account/testprogmath/image/" + uploadedFileImageID));

    }


    @Test
    @Order(15)
    void deleteUnExistingFilesTest() {
        given()
                .spec(requestSpecificationToken)
                .log().uri()
                .when()
                    .delete("/account/{username}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                ///Странно, но вот так себя ведет API, по хорошему тут можно поставить ожидание 404 и это будет баг
                    .spec(responseSpecificationStatusOK);

        given()
                .header("Authorization", token)
                    .log().uri()
                .when()
                    .delete("/account/{username}/image/{imageID}", username, uploadedBase64ImageID)
                    .prettyPeek()
                .then()
                ///Странно, но вот так себя ведет API, по хорошему тут можно поставить ожидание 404 и это будет баг
                    .spec(responseSpecificationStatusOK);
    }


    @Test
    @Order(16)
    void findImagesWithoutAutTokenTest() {
        ResponseInform responseInform = given()
                .header("Authorization", "")
                .expect()
                    .spec(responseSpecification403)
                .when()
                    .get("/account/{userName}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);

        assertThat(responseInform.getData().getError()
                , equalTo("The access token was not found."));

        assertThat(responseInform.getData().getRequest()
                , equalTo("/3/account/testprogmath/image/" + uploadedFileImageID));
    }


    @Test
    @Order(17)
    void findImagesWithoutImageIDTest() {
        ResponseInform responseInform = given()
                .spec(requestSpecificationToken)
                .expect()
                    .spec(responseSpecificationStatusOK)
                .when()
                    .get("/account/{userName}/image/{imageID}", username, "")
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);

        assertThat(responseInform.getData().getError()
                , equalTo("An image ID is required for a GET request to /image"));

        assertThat(responseInform.getData().getRequest()
                , equalTo("/3/account/testprogmath/image/"));
    }

    @Test
    @Order(18)
    void findImagesWithInvalidImageIDTest() {
        ResponseInform responseInform = given()
                .spec(requestSpecificationToken)
                .expect()
                    .spec(responseSpecificationStatusOK)
                .when()
                    .get("/account/{userName}/image/{imageID}", username, "+aa(*^^^%$#^*(")
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);

        assertThat(responseInform.getData().getError()
                , equalTo("An image ID is required for a GET request to /image"));

        assertThat(responseInform.getData().getRequest()
                , equalTo("/3/account/testprogmath/image/+aa(*^^^%$#^*("));
    }


    @Test
    @Order(19)
    void uploadImageBase64WithoutTokenTest() {
        encodedFile = Base64.getEncoder().encodeToString(getFileContent());
        ResponseInform responseInform = given()
                .header("Authorization", "")
                .multiPart("image", encodedFile)
                .formParam("title", imageTitleBase64)
                .expect()
                    .spec(responseSpecification403)
                .when()
                    .post("/image")
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);


        assertThat(responseInform.getData().getError()
                , equalTo("The access token was not found."));

        assertThat(responseInform.getData().getRequest()
                , equalTo("/3/image"));

    }

    @Test
    @Order(20)
    void uploadImageBase64WithInvalidURLTest() {
        encodedFile = Base64.getEncoder().encodeToString(getFileContent());
        given()
                .spec(requestSpecificationToken)
                .multiPart("image", encodedFile)
                .formParam("title", imageTitleBase64)
                .when()
                    .post("/(^%^#$%#%&&*((*)/image")
                    .prettyPeek()
                .then()
                    .spec(responseSpecification404);
    }


    @Test
    @Order(21)
    void deleteImageWithInvalidTokenTest() {
        ResponseInform responseInform = given()
                .header("Authorization", token + "A!")
                .expect()
                    .spec(responseSpecification403)
                .when()
                    .delete("/account/{username}/image/{imageID}", username, uploadedFileImageID)
                    .prettyPeek()
                .then()
                    .extract().as(ResponseInform.class);

        assertThat(responseInform.getData().getError()
                , equalTo("The access token provided is invalid."));

        assertThat(responseInform.getData().getRequest()
                , equalTo("/3/account/testprogmath/image/" + uploadedFileImageID));


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
