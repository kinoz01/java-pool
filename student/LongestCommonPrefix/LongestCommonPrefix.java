public class LongestCommonPrefix {
    public String findLongestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        java.util.Arrays.sort(strs);
        String a = strs[0], b = strs[strs.length - 1];
        int i = 0;
        while (i < a.length() && a.charAt(i) == b.charAt(i)) i++;
        return a.substring(0, i);
    }
}