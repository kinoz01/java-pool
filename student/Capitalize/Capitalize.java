import java.io.*;
import java.nio.file.*;

public class Capitalize {
    public static void capitalize(String[] args) throws IOException {
        if (args == null || args.length != 2)
            return;
        Path in = Paths.get(args[0]), out = Paths.get(args[1]);
        Files.writeString(out, capitalize(Files.readString(in)));
    }

    public static String capitalize(String s) {
        if (s == null)
            return "";
        StringBuilder ret = new StringBuilder();
        for (String w : s.trim().split(" +")) {
            if (!w.isEmpty())
                ret.append(Character.toUpperCase(w.charAt(0)))
                        .append(w.substring(1).toLowerCase())
                        .append(' ');
        }
        return ret.toString().trim();
    }
}
