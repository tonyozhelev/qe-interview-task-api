package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class Photos  extends HTTPClient{

    static final photos = '/photos'

    static Response createPhoto(int albumId, String title) {
        post(photos, TestDataBuilder.photo(albumId,title))
    }
}
