public class DoOp {
    public static String operate(String[] a) {
        if (a == null || a.length != 3)
            return "Error";
        int x = Integer.parseInt(a[0]), y = Integer.parseInt(a[2]);
        return switch (a[1]) {
            case "+" -> "" + (x + y);
            case "-" -> "" + (x - y);
            case "*" -> "" + (x * y);
            case "/" -> y == 0 ? "Error" : "" + (x / y);
            case "%" -> y == 0 ? "Error" : "" + (x % y);
            default -> "Error";
        };
    }
}