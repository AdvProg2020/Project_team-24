package Controller.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EssentialMethods {

    public static Matcher getMatcher(String regex, String input) {
        return Pattern.compile(regex).matcher(input);
    }
}
