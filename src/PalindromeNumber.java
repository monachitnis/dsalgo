public class PalindromeNumber {
    public static boolean isPalindrome(int x) { // does not work for zeros in number e.g. 1001
        //promote to long
        long longx = Math.abs((long) x);
        if (longx < 10) return true;
        long magnitude = 1;
        long num = longx;
        while (num >= 10) {
            num /= 10;
            magnitude *= 10;
        }
        long lsb = longx % 10;
        int msb = (int) (longx / magnitude);
        if (lsb != msb) return false;
        else {
            // strip msb and lsb
            long x1 = (int) ((longx - msb * magnitude) / 10);
            return isPalindrome((int) x1);
        }
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(10101));
        System.out.println(isPalindrome(-2147447412));
    }
}
