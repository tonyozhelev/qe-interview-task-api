package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class Comments  extends HTTPClient{

    static final comments = '/comments'

    static Response createComment(int postId, String name, String email, String body) {
        post(comments, TestDataBuilder.comment(postId, name, email, body))
    }
}
