import java.nio.file.*;
import java.io.*;
import java.util.stream.*;

public class FileSearch {
    public static String searchFile(String fileName) throws IOException {
        Path start = Path.of("documents");
        try (var w = Files.walk(start)) {
            return w.filter(p -> p.getFileName().toString().equals(fileName))
                    .map(p -> "documents/" + start.relativize(p).toString()).findFirst().orElse(null);
        }
    }
}
