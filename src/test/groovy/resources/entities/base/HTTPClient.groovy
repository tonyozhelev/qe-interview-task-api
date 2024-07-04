package resources.entities.base

import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response

class HTTPClient {

  final static baseURI = 'https://jsonplaceholder.typicode.com'

  static {
    RestAssured.baseURI = baseURI
  }

  //Implement any requests that you might need (POST, GET, GET by id etc.)

  static Response post(String resourceURL, String body) {
    return RestAssured
    .given()
    .body(body)
    .log()
    .all()
    .contentType(ContentType.JSON)
    .when()
    .post(resourceURL)
  }


}
