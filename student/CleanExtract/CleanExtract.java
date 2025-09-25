public class CleanExtract {
    public static String extract(String s) {
        return s == null ? ""
                : java.util.Arrays.stream(s.split("\\|"))
                        .map(t -> {
                            int f = t.indexOf('.'), l = t.lastIndexOf('.');
                            t = (f >= 0 && l > f) ? t.substring(f + 1, l) : (f >= 0 ? t.substring(f + 1) : t);
                            return t.trim();
                        })
                        .filter(t -> !t.isEmpty())
                        .collect(java.util.stream.Collectors.joining(" "));
    }
}
