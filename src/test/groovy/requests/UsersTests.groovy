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
    var body = response.body().as(HashMap.class)
    assert body.name == name
    assert body.username == userName
    assert body.email == email
  }
}
