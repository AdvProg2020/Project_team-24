package Controller.Controllers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EssentialMethods {
    protected static Matcher getMatcher(String regex, String input) {
        String validCommand = regex;
        Pattern pattern = Pattern.compile(validCommand);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
