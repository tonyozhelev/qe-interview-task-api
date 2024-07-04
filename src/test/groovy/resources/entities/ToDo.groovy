package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class ToDo extends HTTPClient {

  static final toDos = '/todos'

  static Response createToDo(int userId, String title, boolean completed) {
    post(toDos, TestDataBuilder.userToDo(userId, title, completed))
  }
}
