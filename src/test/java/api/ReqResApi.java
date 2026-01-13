package api;

import dto.*;
import org.junit.jupiter.api.Assertions;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static spec.RegisterSpec.loginRequestSpec;
import static spec.RegisterSpec.responseSpec;

public class ReqResApi {
    public UsersSummaryDTO getAllUsers() {
        return step("request get for all users", () -> given()
                .spec(loginRequestSpec)
                .when()
                .get("users")
                .then()
                .spec(responseSpec(200))
                .extract()
                .as(UsersSummaryDTO.class));
    }

    public void verifyFieldTotalAsExpected(int expectedRes, UsersSummaryDTO response) {
        step("Verify that field TOTAL has expected number", () ->
                Assertions.assertEquals(expectedRes, response.getTotal()));
    }

    public PostUserDTO sendRequestToCreateNewUser(PostUserDTO user) {
        return step("send request to create a new user", () -> given()
                .spec(loginRequestSpec)
                .body(user)
                .when()
                .post("users")
                .then()
                .spec(responseSpec(201))
                .extract()
                .as(PostUserDTO.class));
    }

    public void verifyCreatedNameForUser(PostUserDTO user, PostUserDTO responseUser) {
        step("Verify created name for user", () ->
                Assertions.assertEquals(user.getName(), responseUser.getName()));
    }

    public void verifyCreatedJobForUser(PostUserDTO user, PostUserDTO responseUser) {
        step("Verify created job for user", () ->
                Assertions.assertEquals(user.getJob(), responseUser.getJob()));
    }

    public ErrorDTO logInWithOutPassword(LoginDTO loginUser) {
        return step("Try to login without password", () -> given()
                .spec(loginRequestSpec)
                .body(loginUser)
                .when()
                .post("login")
                .then()
                .spec(responseSpec(400))
                .extract()
                .as(ErrorDTO.class));
    }

    public void verifyErrorText(String expectedErrorText, ErrorDTO response) {
        step("Verify error text", () ->
                Assertions.assertEquals("Missing password", response.getError()));
    }

    public TokenDTO LoginWithCredentials(LoginDTO loginUser) {
        return step("Try to login with credentials", () -> given()
                .spec(loginRequestSpec)
                .body(loginUser)
                .when()
                .post("login")
                .then()
                .spec(responseSpec(200))
                .extract()
                .as(TokenDTO.class));
    }

    public void verifyToken(String expectedToken, TokenDTO tokenDTO) {
        step("Verify token is correct", () ->
                Assertions.assertEquals(expectedToken, tokenDTO.getToken()));
    }

    public void createUserWithNameJobId(PostUserDTO user) {
        step("Create user with name, job and id", () -> given()
                .spec(loginRequestSpec)
                .body(user)
                .when()
                .post("users")
                .then()
                .spec(responseSpec(201))
                .extract()
                .as(PostUserDTO.class));
    }

    public PostUserDTO updateUserNameJobForId(PostUserDTO userPut, PostUserDTO user) {
        return step("Send new name, job for same id", () ->
                given()
                        .spec(loginRequestSpec)
                        .body(userPut)
                        .when()
                        .put("users/" + user.getId())
                        .then()
                        .spec(responseSpec(200))
                        .extract()
                        .as(PostUserDTO.class));
    }

    public PostUserDTO updateUserJobForId(PostUserDTO userPatch, PostUserDTO user) {
        return step("Send new  job for same id", () -> given()
                .spec(loginRequestSpec)
                .body(userPatch)
                .when()
                .patch("users/" + user.getId())
                .then()
                .spec(responseSpec(200))
                .extract()
                .as(PostUserDTO.class));
    }

    public void deleteUserWithId(PostUserDTO user) {
        step("Delete user with name, job and id", () -> given()
                .spec(loginRequestSpec) //loginRequestSpec
                .when()
                .delete("users/2" + user.getId())
                .then()
                .spec(responseSpec(204)));
    }
}


