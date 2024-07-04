package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class Users extends HTTPClient{

  static final users = '/users'
  static final userAlbums = '/users/{userId}/albums'
  static final userPosts = '/users/{userId}/posts'
  static final userToDos = '/users/{userId}/todos'

  static Response createUser(String name, String userName, String email) {
    post(users, TestDataBuilder.user(name, userName, email))
  }

  static Response getAllUsers() {
    get(users)
  }

  static Response getUserAlbums(int userId) {
    get(userAlbums.replaceAll('\\{userId}', userId as String))
  }

  static Response getUserPosts(int userId) {
    get(userPosts.replaceAll('\\{userId}', userId as String))
  }

  static Response getUserToDos(int userId) {
    get(userToDos.replaceAll('\\{userId}', userId as String))
  }
}
