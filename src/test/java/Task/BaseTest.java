package Task;

import Task.Methods.QueryApi;
import Task.Utils.ConfigFileReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

import static Task.Constants.Constants.URL;

public abstract class BaseTest {
    
    public QueryApi queryApi;
    
    @BeforeMethod(alwaysRun = true)
    public void startup() {
        queryApi = new QueryApi();
        ConfigFileReader.initConfig();
        RestAssured.baseURI = ConfigFileReader.getProperty(URL);
    }
    
}