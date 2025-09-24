public class CatInFile {
    public static void cat(String[] args) throws java.io.IOException {
        if (args.length > 0) java.nio.file.Files.copy(System.in, java.nio.file.Path.of(args[0]));
    }
}