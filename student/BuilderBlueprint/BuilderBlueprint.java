import java.util.*;

class ConcreteRegexBuilder implements RegexBuilder {
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

class RegexDirector {
    private RegexBuilder builder;

    public void setBuilder(RegexBuilder b) {
        this.builder = b;
    }

    public Regex construct() {
        builder.buildLiteral("Hello");
        builder.buildWhitespace();
        builder.buildWordCharacter();
        builder.buildAnyCharacter();
        return builder.getResult();
    }
}
