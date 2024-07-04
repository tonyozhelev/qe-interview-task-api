package requests

import io.restassured.response.Response
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import resources.entities.Albums
import resources.entities.Photos
import resources.utils.DataUtils
import resources.utils.UserUtils

class UserResourcesTests {
    static String userId

    @BeforeAll
    static void setup() {
        //prepare user for tests
        userId = UserUtils.createUserReturnId()
    }

    @Test    void test_createAlbumAddPhoto() {
        //prepare album data
        String albumName = DataUtils.createRandomString(10)
        //send request to create album
        Response albumResponse = Albums.createAlbum(userId,albumName)
        //verify request for album was successful
        assert albumResponse.statusCode() == 201
        //verify response data for album
        var albumBody = albumResponse.body().as(HashMap.class)
        assert albumBody.title == albumName
        //prepare data for photo
        String photoName = DataUtils.createRandomString(10)
        //send request to add photo to the album
        Response photoResponse = Photos.createPhoto(albumBody.id.toString(), photoName)
        //verify request for photo was successful
        assert photoResponse.statusCode() == 201
        //verify photo is created in the correct album with correct name
        var photoBody = photoResponse.body().as(HashMap.class)
        assert photoBody.albumId == albumBody.id.toString()
        assert photoBody.title == photoName
    }
}
