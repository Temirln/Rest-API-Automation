package Task.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ConfigFileReader {
    private static JsonNode propertyNode;
    private static final String propertyFile = "src/test/resources/config.json";
    
    public static void initConfig() {
        JSONParser parser = new JSONParser();
        
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JSONObject contentObj = (JSONObject) parser.parse(new FileReader(propertyFile));
            propertyNode = objectMapper.readTree(contentObj.toString());
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static String getProperty(String propertyName) {
        String property = propertyNode.get(propertyName).asText();
        if (property == null) {
            throw new RuntimeException(propertyName + " not specified in the config.json file.");
        } else {
            return property;
        }
    }
    
}