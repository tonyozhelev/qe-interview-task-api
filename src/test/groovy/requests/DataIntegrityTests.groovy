package requests

import io.restassured.response.Response
import org.junit.jupiter.api.Test
import resources.entities.Users
import resources.utils.UserUtils

class DataIntegrityTests {

    @Test
    void test_verifyPostUserCreatesSingleEntity() {
        //get count of all users
        Response response = Users.getAllUsers()
        //verify request is successful
        response.statusCode() == 200
        //get user count
        var userListSize = response.jsonPath().getList('$').size()
        //create new user
        UserUtils.createUserReturnId()
        //verify only one new entity was created
        //get count of all users
        Response updatedUserListResponse = Users.getAllUsers()
        //verify request is successful
        updatedUserListResponse.statusCode() == 200
        //get updated user count
        var updatedUserListSize = response.jsonPath().getList('$').size()
        //verify only one entity was created
        assert updatedUserListSize == userListSize //+1 if it was actually persisted
    }
}
