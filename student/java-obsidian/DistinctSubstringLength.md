```java
public class DistinctSubstringLength {
    public int maxLength(String s) {
        int[] last = new int[1000];
        int best = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            left = Math.max(left, last[s.charAt(i)]);
            best = Math.max(best, i - left + 1);
            last[s.charAt(i)] = i + 1;
        }
        return best;
    }
}
```