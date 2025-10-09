import java.util.*;

public class ConcreteRegexBuilder implements RegexBuilder {
    private final List<String> component = new ArrayList<>();

    @Override
    public void buildLiteral(String s) {
        component.add(s);
    }

    @Override
    public void buildAnyCharacter() {
        component.add(".");
    }

    @Override
    public void buildDigit() {
        component.add("\\d");
    }

    @Override
    public void buildWhitespace() {
        component.add("\\s");
    }

    @Override
    public void buildWordCharacter() {
        component.add("\\w");
    }

    @Override
    public Regex getResult() {
        return new Regex(component);
    }
}
