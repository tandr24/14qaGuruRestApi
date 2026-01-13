import api.ReqResApi;
import dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReqResDataTests extends TestBase {
    @Test
    @DisplayName("Verify that total value after get request equals 12")
    void verifyTotalTest() {
        ReqResApi reqResApi = new ReqResApi();

        UsersSummaryDTO response = reqResApi.getAllUsers();
        reqResApi.verifyFieldTotalAsExpected(12, response);
    }

    @Test
    @DisplayName("New user is added")
    void verifyIfNewUserAddedTest() {
        ReqResApi reqResApi = new ReqResApi();

        PostUserDTO user = new PostUserDTO();
        user.setName("Max");
        user.setJob("programmer");

        PostUserDTO response = reqResApi.sendRequestToCreateNewUser(user);
        reqResApi.verifyCreatedNameForUser(user, response);
        reqResApi.verifyCreatedJobForUser(user, response);
    }

    @Test
    @DisplayName("Unsuccessful login returns error")
    void unsuccessfulLoginReturnsErrorTest() {
        LoginDTO loginUser = new LoginDTO();
        loginUser.setEmail("peter@klaven");

        ReqResApi reqResApi = new ReqResApi();

        ErrorDTO response = reqResApi.logInWithOutPassword(loginUser);
        reqResApi.verifyErrorText("Missing password", response);
    }

    @Test
    @DisplayName("Successful login returns token")
    void verifySuccessfulLoginTest() {
        LoginDTO loginUser = new LoginDTO();
        loginUser.setEmail("eve.holt@reqres.in");
        loginUser.setPassword("cityslicka");

        ReqResApi reqResApi = new ReqResApi();

        TokenDTO tokenDTO = reqResApi.LoginWithCredentials(loginUser);
        reqResApi.verifyToken("QpwL5tke4Pnpja7X4", tokenDTO);
    }

    @Test
    @DisplayName("Verification if method PUT changes name and job for user with specified id")
    void verifyPutForUserNameJobTest() {
        PostUserDTO user = new PostUserDTO();
        user.setName("Max");
        user.setJob("programmer");
        user.setId(2);

        ReqResApi reqResApi = new ReqResApi();
        reqResApi.createUserWithNameJobId(user);

        PostUserDTO userPut = new PostUserDTO();
        userPut.setName("Egor");
        userPut.setJob("tester");

        PostUserDTO responseUserPut = reqResApi.updateUserNameJobForId(userPut, user);

        reqResApi.verifyCreatedJobForUser(userPut, responseUserPut);
        reqResApi.verifyCreatedNameForUser(userPut, responseUserPut);
    }

    @Test
    @DisplayName("Verification if method PATCH changes job for specified user")
    void verifyPatchForUserNameTest() {

        PostUserDTO user = new PostUserDTO();
        user.setName("Max");
        user.setJob("programmer");
        user.setId(2);

        ReqResApi reqResApi = new ReqResApi();
        reqResApi.createUserWithNameJobId(user);

        PostUserDTO userPatch = new PostUserDTO();
        userPatch.setJob("tester");

        PostUserDTO responseUser = reqResApi.updateUserJobForId(userPatch, user);
        reqResApi.verifyCreatedJobForUser(userPatch, responseUser);
    }

    @Test
    @DisplayName("Verification if method DELETE deletes user")
    void verifyDeleteTest() {

        PostUserDTO user = new PostUserDTO();
        user.setName("Max");
        user.setJob("programmer");
        user.setId(2);

        ReqResApi reqResApi = new ReqResApi();
        reqResApi.createUserWithNameJobId(user);
        reqResApi.deleteUserWithId(user);
    }
}
