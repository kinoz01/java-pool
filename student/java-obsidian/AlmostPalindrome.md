```java
public class AlmostPalindrome {
    public static boolean isAlmostPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        return i < j && (p(s, i + 1, j) || p(s, i, j - 1));
    }
    private static boolean p(String s, int i, int j) {
        while (i < j) if (s.charAt(i++) != s.charAt(j--)) return false;
        return true;
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(AlmostPalindrome.isAlmostPalindrome("Racedcar"));
        System.out.println(AlmostPalindrome.isAlmostPalindrome("level"));
    }
}
```

# What `isAlmostPalindrome` does

1.  `s = s.toLowerCase();`  
    Normalize the case so comparisons ignore upper/lower differences (e.g., “Racedcar” → “racedcar”).
    
2.  Find the **first mismatch** from both ends:
    

```java
int i = 0, j = s.length() - 1;
while (i < j && s.charAt(i) == s.charAt(j)) { i++; j--; }
```

-   `i` moves right, `j` moves left.
    
-   Loop stops when:
    
    -   characters differ at `i` and `j`, or
        
    -   `i >= j` which means we crossed/meet in the middle (already a *full palindrome*).
        

3.  “Almost” means: remove **one** char at the mismatch and the rest is a palindrome:
    

```java
return i < j && (p(s, i + 1, j) || p(s, i, j - 1));
```

-   If we actually found a mismatch (`i < j`), try:
    
    -   skip the left char → check `s[i+1..j]`
        
    -   skip the right char → check `s[i..j-1]`
        
-   If either is a palindrome, return true.
    

# The helper `p(s, i, j)`

```java
private static boolean p(String s, int i, int j) {
    while (i < j) if (s.charAt(i++) != s.charAt(j--)) return false;
    return true;
}
```

This checks if the substring `s[i..j]` is a palindrome.

## That compact line explained

`while (i < j) if (s.charAt(i++) != s.charAt(j--)) return false;`

-   It’s just a `while` loop with a single-statement body (`if (...) return false;`).
    
-   `s.charAt(i++)` uses **post-increment**:
    
    -   It fetches the character at the current `i`,
        
    -   then increments `i` **after** reading.
        
-   `s.charAt(j--)` uses **post-decrement**:
    
    -   It fetches the character at the current `j`,
        
    -   then decrements `j` **after** reading.
        

So, on each iteration:

1.  Compare the **current** pair `s[i]` and `s[j]`.
    
2.  Immediately move `i` to `i+1` and `j` to `j-1` (because of `i++` and `j--`).
    
3.  If the pair mismatches → return `false` right away.
    
4.  If no mismatches until `i >= j` → loop ends and we return `true`.
    

It’s equivalent (just shorter) to:

```java
while (i < j) {
    char left = s.charAt(i); 
    char right = s.charAt(j);
    if (left != right) return false;
    i++;
    j--;
}
return true;
```

# Why this algorithm is correct

-   For an “almost” palindrome, there can be **at most one** mismatch that you can fix by removing one char. The first mismatch pinpoints the only place removal can help.
    
-   Checking both `s[i+1..j]` and `s[i..j-1]` covers both possibilities (remove left or right).
    
-   If you never hit a mismatch in the first loop, the string is already a perfect palindrome → per the spec/tests, return `false`.