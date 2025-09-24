import java.nio.file.*;
import java.io.*;
import java.util.stream.*;

public class FileSearch {
    public static String searchFile(String fileName) {
        Path start = Path.of("documents");
        try (Stream<Path> w = Files.walk(start)) {
            return w.filter(p -> p.getFileName().toString().equals(fileName))
                    .map(p -> start.getFileName() + "/"
                            + start.relativize(p).toString().replace(File.separatorChar, '/'))
                    .findFirst().orElse(null);
        } catch (IOException e) {
            return null;
        }
    }
}
