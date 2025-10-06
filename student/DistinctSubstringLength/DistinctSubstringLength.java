public class DistinctSubstringLength {
    public int maxLength(String s) {
        // Implementation to find the length of the longest substring without repeating characters
        int n = s.length();
        int maxLength = 0;
        for (int i = 0; i < n; i++){
            StringBuilder str = new StringBuilder();
            for (int j = i; j < n; j++){
                char c = s.charAt(j);
                boolean exists = false;
                for ()
                str.append(s.charAt(j));
                maxLength = Math.max(maxLength, str.length());
            }
        }
        return maxLength;
    }
}

