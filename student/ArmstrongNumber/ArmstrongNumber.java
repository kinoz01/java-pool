public class ArmstrongNumber {
    public boolean isArmstrong(int number) {
        String s = String.valueOf(number);
        int n = s.length(), sum = 0;
        for (int i = 0; i < n; i++)
            sum += (int) Math.pow(s.charAt(i) - '0', n);
        return sum == number;
    }
}
