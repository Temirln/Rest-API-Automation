package Task.Tests;

import Task.BaseTest;
import Task.Models.PostModel.Post;
import Task.Models.UserModel.User;
import Task.Utils.JsonUtils;
import Task.Utils.QAUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Task.Constants.Constants.POST_ID_COLUMN;
import static Task.Constants.EndPointsConst.*;
import static Task.Constants.PostsExpectedConst.POSTS_EXPECTED_DATA_FILE;
import static Task.Constants.PostsExpectedConst.POST_EXPECTED;
import static Task.Constants.TestsDataConst.*;
import static Task.Constants.UsersExpectedConst.USERS_EXPECTED_DATA_FILE;
import static Task.Constants.UsersExpectedConst.USER_EXPECTED;

public class JSONPlacehoderTest extends BaseTest {
    
    @Test
    public void getPosts() {
        
        Response response = queryApi.get(JsonUtils.getJsonData(ENDPOINTS_DATA_FILE, POSTS_ENDPOINT));
        
        
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
        Assert.assertEquals(response.getContentType(), JsonUtils.getJsonData(TESTS_DATA_FILE, EXPECTED_CONTENT_TYPE), "Response content type isn't json");
        
        List<Post> posts = response.jsonPath().getList("", Post.class);
        
        boolean sorted = QAUtils.isSorted(posts);
        Assert.assertTrue(sorted, "Posts are not sorted by ascending order");
        
    }
    
    @Test
    public void getOnePost() {
        Response response = queryApi.get(JsonUtils.getJsonData(ENDPOINTS_DATA_FILE, POST_ID_ENDPOINT));
        
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK, "Status Code should be " + HttpStatus.SC_OK);
        
        Post postExpected = QAUtils.deserializeToClass(POSTS_EXPECTED_DATA_FILE, POST_EXPECTED, Post.class);
        Post postActual = response.body().as(Post.class);
   
        Assert.assertEquals(postActual, postExpected, "User Id or Id doesn't match");
        Assert.assertFalse(postActual.getTitle().isEmpty(), "Title filed shouldn't be empty");
        Assert.assertFalse(postActual.getBody().isEmpty(), "Response body shouldn't be empty");
        
    }
    
    @Test
    public void get404Post() {
        Response response = queryApi.get(JsonUtils.getJsonData(ENDPOINTS_DATA_FILE, POST_ID_INVALID_ENDPOINT));
        
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND, "Status code should be " + HttpStatus.SC_NOT_FOUND);
        Assert.assertEquals(response.body().asString(), JsonUtils.getJsonData(TESTS_DATA_FILE, EXPECTED_REQUEST_BODY), "Response Body should be empty");
    }
    
    @Test
    public void sendPost() {
        Response response = queryApi.post(Integer.parseInt(JsonUtils.getJsonData(TESTS_DATA_FILE, POST_USER_ID)), JsonUtils.getJsonData(ENDPOINTS_DATA_FILE, POSTS_ENDPOINT));
        
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED, "Status code should be " + HttpStatus.SC_CREATED);
        Assert.assertEquals(response.jsonPath().getString(POST_ID_COLUMN), JsonUtils.getJsonData(TESTS_DATA_FILE, EXPECTED_POST_ID), "Id should be " + JsonUtils.getJsonData(TESTS_DATA_FILE, EXPECTED_POST_ID));
        
        
    }
    
    @Test
    public void getUsersAndGetOneUser() {
        Response response = queryApi.get(JsonUtils.getJsonData(ENDPOINTS_DATA_FILE, USERS_ENDPOINT));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code should be " + HttpStatus.SC_OK);
        Assert.assertEquals(response.getContentType(), JsonUtils.getJsonData(TESTS_DATA_FILE, EXPECTED_CONTENT_TYPE), "Response isn't json");
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<User> allUsers = jsonPathEvaluator.getList("", User.class);
        
        User userIdFive = null;
        
        for (User user : allUsers) {
            if (user.getId() == Integer.parseInt(JsonUtils.getJsonData(TESTS_DATA_FILE, EXPECTED_USER_ID))) {
                userIdFive = user;
            }
        }
        
        User compareUser = QAUtils.deserializeToClass(USERS_EXPECTED_DATA_FILE, USER_EXPECTED, User.class);
        
        Assert.assertEquals(userIdFive, compareUser, "User data does not equal");
        
    }
    
    @Test
    public void getOneUser() {
        Response response = queryApi.get(JsonUtils.getJsonData(ENDPOINTS_DATA_FILE, USERS_ID_ENDPOINT));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, "Status code should be " + HttpStatus.SC_OK);
        
        User compareUser = QAUtils.deserializeToClass(USERS_EXPECTED_DATA_FILE, USER_EXPECTED, User.class);
        User userFive = response.jsonPath().getObject("", User.class);
        
        Assert.assertEquals(compareUser, userFive, "User data does not equal");
    }
}
