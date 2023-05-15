package Task.Methods;

import Task.Utils.RandomUtils;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static Task.Constants.Constants.*;

public class QueryRequest {
    
    private static final Map<String, String> map = new HashMap<>();
    
    public static RequestSpecification generatePostRequest(int userId) {
        RequestSpecification request = RestAssured.given();
        
        map.put(POSTS_USER_ID_COLUMN, String.valueOf(userId));
        map.put(POSTS_TITLE_COLUMN, RandomUtils.randomTitle());
        map.put(POSTS_BODY_COLUMN, RandomUtils.randomText());
        
        request.body(map);
        
        return request;
    }
    
}
