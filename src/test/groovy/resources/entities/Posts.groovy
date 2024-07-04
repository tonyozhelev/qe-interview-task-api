package resources.entities

import io.restassured.response.Response
import resources.entities.base.HTTPClient
import resources.utils.TestDataBuilder

class Posts extends HTTPClient{

    static final posts = '/posts'
    static final postComments = '/posts/{postId}/comments'

    static Response createPost(int userId, String postTitle, String postBody) {
        post(posts, TestDataBuilder.post(userId,postTitle,postBody))
    }

    static Response getCommentsForPost(int postId) {
        get(postComments.replaceAll('\\{postId}',postId.toString()))
    }
}
