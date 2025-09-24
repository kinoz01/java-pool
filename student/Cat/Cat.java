public class Cat {
    public static void cat(String[] args) throws java.io.IOException {
        if (args.length == 0) return;
        try (var in = new java.io.FileInputStream(args[0])) {
            in.transferTo(System.out);
        }
    }
}
