package requests

import io.restassured.response.Response
import org.junit.jupiter.api.Test
import resources.entities.Users
import resources.utils.DataUtils
import resources.utils.UserUtils


class UsersTests{

  @Test
  void test_createUser() {
    //prepare user data
    String name = DataUtils.createRandomName()
    String userName = DataUtils.createRandomUserName()
    String email = DataUtils.createRandomEmail()
    //send user request
    Response response = Users.createUser(name,userName,email)
    //verify request is successful
    assert response.statusCode() == 201
    //verify response data
    var body = response.body().jsonPath().getMap('$')
    assert body.name == name
    assert body.username == userName
    assert body.email == email
  }


  //better approach will be to take count directly from DB if there are many records
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
