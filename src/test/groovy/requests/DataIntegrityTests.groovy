package requests

import io.restassured.response.Response
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import resources.entities.Albums
import resources.entities.Comments
import resources.entities.Photos
import resources.entities.Posts
import resources.entities.ToDo
import resources.entities.Users
import resources.utils.DataUtils
import resources.utils.UserUtils

class DataIntegrityTests {

    static int userId

    @BeforeAll
    static void setup() {
        userId = UserUtils.createUserReturnId()
    }

    @Test
    void test_createUserCreatesSingleEntity() {
        //get all users
        Response response = Users.getAllUsers()
        //verify request is successful
        assert response.statusCode() == 200
        //get user count
        var userListSize = response.jsonPath().getList('$').size()
        //create new user
        UserUtils.createUserReturnId()
        //get all users after update
        Response updatedUserListResponse = Users.getAllUsers()
        //verify request is successful
        assert updatedUserListResponse.statusCode() == 200
        //verify only one entity was created
        var updatedUserListSize = response.jsonPath().getList('$').size()
        assert updatedUserListSize == userListSize //+1 if it was actually persisted
    }

    @Test
    void test_createPhotosAndAlbumsCreatesSingleEntity() {
        //get all albums for user
        Response allAlbumsResponse = Users.getUserAlbums(userId)
        //verify response
        assert allAlbumsResponse.statusCode() == 200
        //get count of albums
        int albumCount = allAlbumsResponse.jsonPath().getList('$').size()
        //send request to create album
        String albumName = DataUtils.createRandomString(10)
        Response albumResponse = Albums.createAlbum(userId,albumName)
        //verify request for album was successful
        assert albumResponse.statusCode() == 201
        //get all albums after update
        Response allAlbumsResponseAfterUpdate = Users.getUserAlbums(userId)
        //verify response
        assert allAlbumsResponseAfterUpdate.statusCode() == 200
        //check only one new album was created
        int albumCountAfterUpdate = allAlbumsResponse.jsonPath().getList('$').size()
        assert albumCountAfterUpdate == albumCount //+1 in real scenarios
        //get all photos in album
        int albumId = albumResponse.body().jsonPath().getMap('$').id
        Response allPhotosResponse = Albums.getPhotosInAlbum(albumId)
        //verify response
        assert allPhotosResponse.statusCode() == 200
        //get count of photos
        int photosCount = allPhotosResponse.jsonPath().getList('$').size()
        //send request to add photo to the album
        String photoName = DataUtils.createRandomString(10)
        Response photoResponse = Photos.createPhoto(albumId, photoName)
        //verify request for photo was successful
        assert photoResponse.statusCode() == 201
        //get all photos in album after update
        Response allPhotosResponseAfterUpdate = Albums.getPhotosInAlbum(albumId)
        //verify response
        assert allPhotosResponseAfterUpdate.statusCode() == 200
        //check only one photo was created
        int photosCountAfterUpdate = allPhotosResponseAfterUpdate.jsonPath().getList('$').size()
        assert photosCountAfterUpdate == photosCount //+1
    }

    @Test
    void test_createPostAndCommentCreatesSingleEntity() {
        //get all posts for user
        Response postsForUser = Users.getUserPosts(userId)
        //verify request is successful
        postsForUser.statusCode() == 200
        //get posts count
        int postsCount = postsForUser.jsonPath().getList('$').size()
        //prepare post data
        String postTitle = DataUtils.createRandomString(15)
        String postBody = DataUtils.createRandomString(50)
        //send post request
        Response postResponse = Posts.createPost(userId,postTitle,postBody)
        //verify post request was successful
        assert postResponse.statusCode() == 201
        //get all posts for user after update
        Response postsForUserAfterUpdate = Users.getUserPosts(userId)
        //verify request is successful
        postsForUserAfterUpdate.statusCode() == 200
        //verify only one post was created
        int postsCountAfterUpdate = postsForUserAfterUpdate.jsonPath().getList('$').size()
        assert postsCountAfterUpdate == postsCount //+1
        //get all comments for post
        int postId = postResponse.body().jsonPath().getMap('$').id
        Response commentsForPost = Posts.getCommentsForPost(postId)
        //verify request is successful
        commentsForPost.statusCode() == 200
        //get comments count
        int commentsCount = commentsForPost.jsonPath().getList('$').size()
        //send request to add comment to post
        String commentName = DataUtils.createRandomString(10)
        String commentEmail = DataUtils.createRandomEmail()
        String commentBody = DataUtils.createRandomString(50)
        Response commentResponse = Comments.createComment(postId, commentName, commentEmail, commentBody)
        //verify request was successful
        assert commentResponse.statusCode() == 201
        //get all comments for post after update
        Response commentsForPostAfterUpdate = Posts.getCommentsForPost(postId)
        //verify request is successful
        commentsForPostAfterUpdate.statusCode() == 200
        //verify only one comment was created
        int commentsCountAfterUpdate = commentsForPostAfterUpdate.jsonPath().getList('$').size()
        assert commentsCountAfterUpdate == commentsCount //+1
    }

    @Test
    void test_createUserToDoCreatesSingleEntity() {
        //get all to dos for user
        Response todosForUser = Users.getUserToDos(userId)
        //verify request is successful
        assert todosForUser.statusCode() == 200
        //get to dos count
        int todoCount = todosForUser.jsonPath().getList('$').size()
        //create to do
        String title = DataUtils.createRandomString(10)
        boolean completed = false
        Response response = ToDo.createToDo(userId, title, completed)
        //verify response was successful
        assert response.statusCode() == 201
        //get to dos after update
        Response todosForUserAfterUpdate = Users.getUserToDos(userId)
        //verify request is successful
        assert todosForUserAfterUpdate.statusCode() == 200
        //verify only one to do was created
        int todoCountAfterUpdate = todosForUser.jsonPath().getList('$').size()
        assert todoCountAfterUpdate == todoCount //+1
    }
}
