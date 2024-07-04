package resources.utils

class TestDataBuilder {

  /**
   * In this class you will have to write methods that will returns the objects that you want to use in your requests
   */


  static String userToDo(int userId, String title, boolean completed) {
    return "  {\n" +
      "    \"userId\": " + userId + ",\n" +
      "    \"title\": \"" + title + "\",\n" +
      "    \"completed\": " + completed + "\n" +
      "  }"
  }

  //Complete the user like the ToDos above

  static String user(String name, String userName, String email) {
    return " {\n" +
      "    \"name\": \"${name}\",\n" +
      "    \"username\": \"${userName}\",\n" +
      "    \"email\": \"${email}\",\n" +
      "    \"address\": {\n" +
      "      \"street\": \"Kulas Light\",\n" +
      "      \"suite\": \"Apt. 556\",\n" +
      "      \"city\": \"Gwenborough\",\n" +
      "      \"zipcode\": \"92998-3874\",\n" +
      "      \"geo\": {\n" +
      "        \"lat\": \"-37.3159\",\n" +
      "        \"lng\": \"81.1496\"\n" +
      "      }\n" +
      "    },\n" +
      "    \"phone\": \"1-770-736-8031 x56442\",\n" +
      "    \"website\": \"hildegard.org\",\n" +
      "    \"company\": {\n" +
      "      \"name\": \"Romaguera-Crona\",\n" +
      "      \"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
      "      \"bs\": \"harness real-time e-markets\"\n" +
      "    }\n" +
      "  }"
  }


  static String album(int userId, String title) {
    return "{\n" +
            "\t\"userId\" : ${userId},\n" +
            "\t\"title\" : \"${title}\"\n" +
            "}"
  }

  static String photo(int albumId, String title) {
    return "{\n" +
            "\t\"albumId\" : ${albumId},\n" +
            "\t\"title\" : \"${title}\"\n" +
            "}"
  }

  static String post(int userId, String title, String body) {
    return "{\n" +
            "    \"userId\": ${userId},\n" +
            "    \"title\": \"${title}\",\n" +
            "    \"body\": \"${body}\"\n" +
            "}"
  }

  static String comment(int postId, String name, String email, String body) {
    return "{\n" +
            "    \"postId\": ${postId},\n" +
            "    \"name\": \"${name}\",\n" +
            "    \"email\": \"${email}\",\n" +
            "    \"body\": \"${body}\"\n" +
            "  }"
  }
}
