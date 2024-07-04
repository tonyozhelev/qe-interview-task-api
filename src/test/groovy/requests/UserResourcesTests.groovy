package requests

import io.restassured.response.Response
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import resources.entities.Albums
import resources.entities.Comments
import resources.entities.Photos
import resources.entities.Posts
import resources.entities.ToDo
import resources.utils.DataUtils
import resources.utils.UserUtils


class UserResourcesTests {
    static int userId

    @BeforeAll
    static void setup() {
        //prepare user for tests
        userId = UserUtils.createUserReturnId()
    }

    @Test
    void test_createAlbumAddPhoto() {
        //prepare album data
        String albumName = DataUtils.createRandomString(10)
        //send request to create album
        Response albumResponse = Albums.createAlbum(userId,albumName)
        //verify request for album was successful
        assert albumResponse.statusCode() == 201
        //verify response data for album
        var albumResponseBody = albumResponse.body().jsonPath().getMap('$')
        assert albumResponseBody.title == albumName
        //prepare data for photo to be added to album
        int albumId = albumResponseBody.id
        String photoName = DataUtils.createRandomString(10)
        //send request to add photo to the album
        Response photoResponse = Photos.createPhoto(albumId, photoName)
        //verify request for photo was successful
        assert photoResponse.statusCode() == 201
        //verify photo is created in the correct album with correct name
        var photoResponseBody = photoResponse.body().jsonPath().getMap('$')
        assert photoResponseBody.albumId == albumId
        assert photoResponseBody.title == photoName
    }

    @Test
    void test_createPostAndComment() {
        //prepare post data
        String postTitle = DataUtils.createRandomString(15)
        String postBody = DataUtils.createRandomString(50)
        //send post request
        Response postResponse = Posts.createPost(userId,postTitle,postBody)
        //verify post request was successful
        assert postResponse.statusCode() == 201
        //verify post response data
        var postResponseBody = postResponse.body().jsonPath().getMap('$')
        assert postResponseBody.userId == userId
        assert postResponseBody.title == postTitle
        assert postResponseBody.body == postBody
        //prepare data for comment to be added to post
        int postId = postResponseBody.id
        String commentName = DataUtils.createRandomString(10)
        String commentEmail = DataUtils.createRandomEmail()
        String commentBody = DataUtils.createRandomString(50)
        //send request to add comment to post
        Response commentResponse = Comments.createComment(postId, commentName, commentEmail, commentBody)
        //verify request was successful
        assert commentResponse.statusCode() == 201
        //verify response data is correct
        var commentResponseBody = commentResponse.body().jsonPath().getMap('$')
        assert commentResponseBody.postId == postId
        assert commentResponseBody.name == commentName
        assert commentResponseBody.email == commentEmail
        assert commentResponseBody.body == commentBody
    }

    @Test
    void test_createUserToDo() {
        String title = DataUtils.createRandomString(10)
        boolean completed = false
        Response response = ToDo.createToDo(userId, title, completed)
        assert response.statusCode() == 201
        var toDoBody = response.body().jsonPath().getMap('$')
        assert toDoBody.userId == userId
        assert toDoBody.title == title
        assert toDoBody.completed == completed
    }
}
