public class IndexOf {
    public static int strStr(String haystack, String needle) {
        if(null == needle || needle.length() == 0) return 0;
        int found = -1;
        for(int h = 0; h < haystack.length(); h++) {
            found = -1;
            int n = 0;
            while(n < needle.length() && (h+n) < haystack.length()) {
                if(needle.charAt(n) == haystack.charAt(h+n)) {
                    n++;
                } else {
                    break;
                }
            }
            if(n == needle.length()) {
                found = h;
                break;
            }
        }
        return found;
    }

    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "issip"));
    }
}
