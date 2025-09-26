public class UnitConverter {
    public static String convert(String []args) {
        if (args.length != 3) return "ERROR";
        double v;
        try {
            v = Double.parseDouble(args[2]);
        } catch (Exception e) {
            return "ERROR";
        }
        double r = switch(args[0] + "->" + args[1]) {
            case "celsius->fahrenheit" -> v * 9 / 5 + 32;
            case "fahrenheit->celsius" -> (v - 32) * 5 / 9;
            case "kilometers->miles" -> v * 0.621371;
            case "miles->kilometers" -> v * 1.60934;
            case "pounds->kilograms" -> v * 0.45359237;
            default -> 0;
        };
        return r == 0 ? "ERROR" : String.format("%.2f", r);
    }
}