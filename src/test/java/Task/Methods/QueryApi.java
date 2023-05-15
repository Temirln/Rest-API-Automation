package Task.Methods;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class QueryApi {
    
    public Response get(String endPoint) {
        
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(endPoint)
                .then()
                .extract().response();
    }
    
    public Response post(int userId, String endPoint) {
        RequestSpecification request = QueryRequest.generatePostRequest(userId);
        
        return request.post(endPoint);
    }
}
