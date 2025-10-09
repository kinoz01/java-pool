public class FirstUnique {
    public char findFirstUnique(String s) {
        int[] cnt = new int[65536];
        for (char c: s.toCharArray()) cnt[c]++;
        for (char c: s.toCharArray()) if (cnt[c] == 1) return c;
        return '_';
    }
}