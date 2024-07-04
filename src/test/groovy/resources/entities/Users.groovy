package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class Users extends HTTPClient{

  static final users = '/users'

  static Response createUser(String name, String userName, String email) {
    post(users, TestDataBuilder.user(name, userName, email))
  }



}
