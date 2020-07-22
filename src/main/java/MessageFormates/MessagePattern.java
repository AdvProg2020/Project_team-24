package MessageFormates;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface MessagePattern {

    Pattern splitTypeRequest = Pattern.compile("^" + "(.+)::(.+) (.*)" + "$");

    Function<String, Matcher> messageMatcher = splitTypeRequest::matcher;

    default List<String> readMessage(String message) {
        Matcher matcher = messageMatcher.apply(message);
        if (!matcher.find()) return null;
        return Arrays.asList(matcher.group(1), matcher.group(2), matcher.group(3));
    }
}
