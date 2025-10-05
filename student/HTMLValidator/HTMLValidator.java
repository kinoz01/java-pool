import java.util.*;
import java.util.regex.*;

public class HTMLValidator {
    private static final Pattern TAG = Pattern.compile("(?i)<(/?)(html|body|div|p|b|i|h1|h2)>");

    public boolean validateHTML(String html) {
        if (html == null || html.isEmpty())
            return false;

        List<String> stack = new ArrayList<>();
        Matcher m = TAG.matcher(html);
        while (m.find()) {
            String t = m.group(2).toLowerCase();
            if (m.group(1).isEmpty())
                stack.add(t);
            else if (stack.isEmpty() || !stack.remove(stack.size() - 1).equals(t))
                return false;
        }
        return stack.isEmpty();
    }
}
