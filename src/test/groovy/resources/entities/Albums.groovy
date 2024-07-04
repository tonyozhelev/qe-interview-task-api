package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class Albums extends HTTPClient{

    static final albums = '/albums'

    static Response createAlbum(String userId, String albumName) {
        post(albums, TestDataBuilder.album(userId,albumName))
    }
}
