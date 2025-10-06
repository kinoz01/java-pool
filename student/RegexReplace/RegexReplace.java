public class RegexReplace {
    public static String removeUnits(String s) {
        return s.replaceAll("(?<=\\d)(?:cm|€)(?=\\s|$)", "");
    }

    public static String obfuscateEmail(String s) {
        String[] parts = s.split("@", 2);
        if (parts.length != 2) return s;
        String user = parts[0], dom = parts[1];

        // username
        int i = Math.max(Math.max(user.lastIndexOf('.'), user.lastIndexOf('-')), user.lastIndexOf('_'));
        if (i >= 0 && i < user.length() - 1) {
            user = user.substring(0, i + 1) + "*".repeat(user.length() - i - 1);
        } else if (user.length() > 3) {
            user = user.substring(0, 3) + "*".repeat(user.length() - 3);
        }

        // domain
        String[] d = dom.split("\\.");
        d[0] = "*".repeat(d[0].length());
        if (d.length == 3) {
            d[2] = "*".repeat(d[2].length());
        } else {
            String tld = d[1];
            if (!(tld.equals("com") || tld.equals("org") || tld.equals("net"))) {
                d[1] = "*".repeat(d[1].length());
            }
        }
        return user + "@" + String.join(".", d);
    }
}
