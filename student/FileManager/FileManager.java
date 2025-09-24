import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileManager {
    public static void createFile(String fileName, String content) throws IOException {
        Files.writeString(Path.of(fileName), content);
    }
    public static String getContentFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }
    public static void deleteFile(String fileName) {
        File fyl = new File(fileName);
        fyl.delete();
    }
}