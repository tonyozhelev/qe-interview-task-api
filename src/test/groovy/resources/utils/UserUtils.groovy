package resources.utils

import io.restassured.response.Response
import resources.entities.Users

class UserUtils {
    static int createUserReturnId() {
        String name = DataUtils.createRandomName()
        String userName = DataUtils.createRandomUserName()
        String email = DataUtils.createRandomEmail()
        Response response = Users.createUser(name,userName,email)
        assert response.statusCode() == 201
        var body = response.body().jsonPath().getMap('$')
        return body.id
    }
}
