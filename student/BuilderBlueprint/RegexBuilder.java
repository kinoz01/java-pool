public interface RegexBuilder {
    void buildLiteral(String literal);
    void buildAnyCharacter();
    void buildDigit();
    void buildWhitespace();
    void buildWordCharacter();
    Regex getResult();
}