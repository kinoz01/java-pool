import java.nio.file.*;

public class CatInFile {
    public static void cat(String[] args) throws java.io.IOException {
        if (args.length > 0) Files.copy(System.in, Path.of(args[0]));
    }
}