public class Palindrome {
    public static boolean isPalindrome(String s) {
        return s != null && new StringBuilder(s).reverse().toString().equalsIgnoreCase(s);
    }
}
