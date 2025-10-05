import java.util.*;

public class Regex {
    private final StringBuilder pattern;

    public Regex() {
        this.pattern = new StringBuilder();
    }

    public Regex(List<String> component) {
        this();
        if (component != null)
            for (String s : component)
                pattern.append(s);
    }

    public String getPattern() {
        return pattern.toString();
    }

    @Override
    public String toString() {
        return getPattern();
    }
}