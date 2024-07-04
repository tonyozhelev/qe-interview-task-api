package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class ToDo extends HTTPClient {

  final toDos = '/todos'

  Response createToDo(int userId, String title, boolean completed) {
    post(toDos, TestDataBuilder.userToDo(userId, title, completed))
  }
}
