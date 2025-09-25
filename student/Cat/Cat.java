import java.nio.file.*;

public class Cat {
    public static void cat(String[] args) throws java.io.IOException {
        if (args.length > 0) Files.copy(Path.of(args[0]), System.out);
    }
}
