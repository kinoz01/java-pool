public class CleanExtract {
    public static String extract(String s) {
        if (s == null) {
            return "";
        }

        String[] parts = s.split("\\|"); // split input into tokens
        StringBuilder result = new StringBuilder(); // to build the final output

        for (String t : parts) {
            int f = t.indexOf('.');
            int l = t.lastIndexOf('.');

            if (f >= 0 && l > f) {
                t = t.substring(f + 1, l); // between first and last dot
            } else if (f >= 0) {
                t = t.substring(f + 1); // after only one dot
            }

            t = t.trim(); // remove spaces

            if (!t.isEmpty()) { // skip empties
                if (result.length() > 0) {
                    result.append(" "); // add separator
                }
                result.append(t);
            }
        }
        return result.toString();
    }
}
