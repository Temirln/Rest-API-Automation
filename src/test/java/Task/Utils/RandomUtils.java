package Task.Utils;

import java.util.Random;

import static Task.Constants.TestsDataConst.*;

public class RandomUtils {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";
    
    
    public static String randomTitle() {
        String combination = LOWER + UPPER;
        return randomizeContent(Integer.parseInt(JsonUtils.getJsonData(TESTS_DATA_FILE, TITLE_LENGHT)), combination);
    }
    
    public static String randomText() {
        String combination = UPPER + LOWER + DIGITS + PUNCTUATION;
        return randomizeContent(Integer.parseInt(JsonUtils.getJsonData(TESTS_DATA_FILE, TEXT_LENGHT)), combination);
    }
    
    public static String randomizeContent(int len, String combination) {
        char[] text = new char[len];
        Random r = new Random();
        for (int i = 0; i < len; i++) {
            text[i] = combination.charAt(r.nextInt(combination.length()));
        }
        
        return String.valueOf(text);
    }
    
}