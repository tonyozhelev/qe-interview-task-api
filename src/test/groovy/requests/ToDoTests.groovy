package requests

import io.restassured.response.Response

import org.junit.jupiter.api.Test
import resources.ToDo

class ToDoTests extends ToDo{

  @Test
  void test_createUserToDo() {

    Response response = createToDo(1, "Test Title", false)
    response.statusCode() == 200
    response.prettyPrint()
  }
}
