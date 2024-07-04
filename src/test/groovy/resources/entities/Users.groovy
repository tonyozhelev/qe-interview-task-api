package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class Users extends HTTPClient{

  static final users = '/users'
  static final userById = '/users/{userId}'

  static Response createUser(String name, String userName, String email) {
    post(users, TestDataBuilder.user(name, userName, email))
  }

  static Response getAllUsers() {
    get(users)
  }

  static Response getUserById(int userId) {
    get(userById.replaceAll('\\{userId}', userId as String))
  }


}
