import java.util.List;

public class ConfigProtector {
    public String hideSensitiveData(String configFile, List<String> sensitiveKeys) {
        if (configFile == null || sensitiveKeys == null || sensitiveKeys.isEmpty())
            return configFile;

        String[] lines = configFile.split("\n", -1);
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            int eq = line.indexOf('=');
            if (eq >= 0) {
                String key = line.substring(0, eq);
                if (sensitiveKeys.contains(key)) {
                    int len = line.length() - (eq + 1); // value length
                    line = key + "=" + "*".repeat(len); // mask with same length
                }
            }
            out.append(line);
            if (i < lines.length - 1)
                out.append('\n'); // preserve newlines exactly
        }
        return out.toString();
    }
}
