import DTO.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static spec.RegisterSpec.*;

public class ReqResDataTests extends TestBase {

    @Test
    @DisplayName("Verify that total value after get request equals 12")
    void verifyTotalTest() {
        UsersSummaryDTO response = step("request get for all users", () -> given()
                .spec(loginRequestSpec)
                .when()
                .get("users")
                .then()
                .spec(responseSpec200)
                .extract()
                .as(UsersSummaryDTO.class));

        step("Verify that field TOTAL is 12", () ->
                Assertions.assertEquals(12, response.getTotal()));
    }

    @Test
    @DisplayName("New user is added")
    void verifyIfNewUserAddedTest() {
        PostUserDTO user = new PostUserDTO();
        user.setName("Max");
        user.setJob("programmer");

        PostUserDTO responseUser = step("send request to create a new user", () -> given()
                .spec(loginRequestSpec)
                .body(user)
                .when()
                .post("users")
                .then()
                .spec(responseSpec201)
                .extract()
                .as(PostUserDTO.class));

        step("Verify created name for user", () ->
                Assertions.assertEquals(user.getName(), responseUser.getName()));
        step("Verify created job for user", () ->
                Assertions.assertEquals(user.getJob(), responseUser.getJob()));
    }

    @Test
    @DisplayName("Unsuccessful login returns error")
    void unsuccessfulLoginReturnsErrorTest() {
        LoginDTO loginUser = new LoginDTO();
        loginUser.setEmail("peter@klaven");

        ErrorDTO response = step("Try to login without password", () -> given()
                .spec(loginRequestSpec)
                .body(loginUser)
                .when()
                .post("login")
                .then()
                .spec(responseSpec400)
                .extract()
                .as(ErrorDTO.class));
        step("Verify error text", () ->
                Assertions.assertEquals("Missing password", response.getError()));
    }

    @Test
    @DisplayName("Successful login returns token")
    void verifySuccessfulLoginTest() {
        LoginDTO loginUser = new LoginDTO();
        loginUser.setEmail("eve.holt@reqres.in");
        loginUser.setPassword("cityslicka");

        TokenDTO tokenDTO = step("Try to login with credentials", () -> given()
                .spec(loginRequestSpec)
                .body(loginUser)
                .when()
                .post("login")
                .then()
                .spec(responseSpec200)
                .extract()
                .as(TokenDTO.class));

        step("Verify token is correct", () ->
                Assertions.assertEquals("QpwL5tke4Pnpja7X4", tokenDTO.getToken()));
    }

    @Test
    @DisplayName("Verification status code for user, which doesn't exist")
    void verifyStatusCodeForNonExistedUserTest() {
        step("Verification status code for user, which doesn't exist", () -> given()
                .spec(loginRequestSpec)
                .when()
                .get("user/23")
                .then()
                .spec(responseSpec404));
    }

    @Test
    @DisplayName("Verification if method PUT changes name and job for user with id 2")
    void verifyPutForUserNameJobTest() {
        PostUserDTO user = new PostUserDTO();
        user.setName("Max");
        user.setJob("programmer");
        user.setId(2);

        step("Create user with name, job and id", () -> given()
                .spec(loginRequestSpec)
                .body(user)
                .when()
                .post("users")
                .then()
                .spec(responseSpec201)
                .extract()
                .as(PostUserDTO.class));

        PostUserDTO userPut = new PostUserDTO();
        userPut.setName("Egor");
        userPut.setJob("tester");

        PostUserDTO responseUserPut = step("Send new name, job for same id", () ->
                given()
                        .spec(loginRequestSpec)
                        .body(userPut)
                        .when()
                        .put("users/2")
                        .then()
                        .spec(responseSpec200)
                        .extract()
                        .as(PostUserDTO.class));

        step("Verify that name is changed", () ->
                Assertions.assertEquals(userPut.getName(), responseUserPut.getName()));
        step("Verify that job is changed", () ->
                Assertions.assertEquals(userPut.getJob(), responseUserPut.getJob()));

    }

    @Test
    @DisplayName("Verification if method PATCH changes job for user with id 2")
    void verifyPatchForUserNameTest() {

        PostUserDTO user = new PostUserDTO();
        user.setName("Max");
        user.setJob("programmer");
        user.setId(2);

        step("Create user with name, job and id", () -> given()
                .spec(loginRequestSpec)
                .body(user)
                .when()
                .post("users")
                .then()
                .spec(responseSpec201)
                .extract()
                .as(PostUserDTO.class));

        PostUserDTO userPatch = new PostUserDTO();
        userPatch.setJob("tester");

        PostUserDTO responseUser = step("Send new  job for same id", () -> given()
                .spec(loginRequestSpec)
                .body(userPatch)
                .when()
                .patch("users/2")
                .then()
                .spec(responseSpec200)
                .extract()
                .as(PostUserDTO.class));
        step("Verify that job is changed", () ->
                Assertions.assertEquals(userPatch.getJob(), responseUser.getJob()));
    }

    @Test
    @DisplayName("Verification if method DELETE delete user with id 2")
    void verifyDeleteTest() {

        PostUserDTO user = new PostUserDTO();
        user.setName("Max");
        user.setJob("programmer");
        user.setId(2);

        step("Create user with name, job and id", () -> given()
                .spec(loginRequestSpec)
                .body(user)
                .when()
                .post("users")
                .then()
                .spec(responseSpec201));

        step("Delete user with name, job and id", () -> given()
                .spec(loginRequestSpec) //loginRequestSpec
                .when()
                .delete("users/2")
                .then()
                .spec(responseSpec204));

    }
}
