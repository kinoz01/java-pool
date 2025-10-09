public class RegexDirector {
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