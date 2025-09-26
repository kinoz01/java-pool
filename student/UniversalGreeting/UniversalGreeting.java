public class UniversalGreeting {
    public static String greeting(String lang) {
        return switch(lang) {
            case "EN" -> "Hello, How are you?";
            case "FR" -> "Bonjour comment allez-vous?";
            case "ES" -> "Hola, cómo estás?";
            default -> "";
        };
    }
}