```java
public class UnitConverter {
    public static String convert(String[] args) {
        if (args == null || args.length != 3)
            return "ERROR";
        double v;
        try {
            v = Double.parseDouble(args[2]);
        } catch (Exception e) {
            return "ERROR";
        }
        double r = switch ((args[0] + "->" + args[1]).toLowerCase()) {
            case "celsius->fahrenheit" -> v * 9 / 5 + 32;
            case "fahrenheit->celsius" -> (v - 32) * 5 / 9;
            case "kilometers->miles" -> v * 0.621371;
            case "miles->kilometers" -> v * 1.60934;
            case "pounds->kilograms" -> v * 0.45359237;
            default -> Double.NaN;
        };
        return Double.isNaN(r) ? "ERROR" : String.format("%.2f", r);
    }
}
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(UnitConverter.convert(new String[]{"celsius", "fahrenheit", "100"}));
        System.out.println(UnitConverter.convert(new String[]{"fahrenheit", "celsius", "212"}));
        System.out.println(UnitConverter.convert(new String[]{"kilometers", "miles", "5"}));
        System.out.println(UnitConverter.convert(new String[]{"pounds", "kilograms", "10"}));
        System.out.println(UnitConverter.convert(args));
    }
}
```

Note that instead of `Double.NaN` and `Double.isNaN(r)` you can return `0` and check `r == 0` 