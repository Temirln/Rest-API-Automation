package Task.Utils;

import Task.Models.PostModel.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class QAUtils {
    
    public static boolean isSorted(List<Post> posts) {
        boolean sorted = true;
        
        for (int i = 0; i < posts.size() - 1; i++) {
            if (posts.get(i).getId() > posts.get(i + 1).getId()) {
                sorted = false;
                break;
            }
        }
        
        return sorted;
    }
    
    public static <T> T deserializeToClass(String dataFile, String object, Class<T> o) {
        
        try {
            return new ObjectMapper().readValue(JsonUtils.getJsonData(dataFile, object), o);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        
    }
}