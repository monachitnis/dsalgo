import java.util.*;

public class GenerateParenthesis {
    public static ArrayList<String> generateParenthesis(int A) {
        return helper(A, new HashMap<>());
    }

    public static ArrayList<String> helper(int A, Map<Integer, List<String>> memoize) {
        int length = A * 2;
        Set<String> paren = new HashSet<>(); //dedupe
        // base cases
        if (A == 0) return new ArrayList<>();
        if (A == 1) {
            paren.add("()");
            return new ArrayList<>(paren);
        }

        for (int i = 1; i < A; i++) {
            // possible paren are (1, n-1), (n-1, 1), "(" + n-1 + ")"
            List<String> set1;
            List<String> set2;
            if (memoize.containsKey(i)) {
                set1 = memoize.get(i);
            } else {
                set1 = generateParenthesis(i);
                memoize.put(i, set1);
            }
            if (memoize.containsKey(A - i)) {
                set2 = memoize.get(A - i);
            } else {
                set2 = generateParenthesis(A - i);
                memoize.put(A - i, set2);
            }
            for (String s1 : set1)
                for (String s2 : set2)
                    paren.add(s1 + s2);
        }
        List<String> set3 = memoize.get(A - 1);
        for (String s : set3) {
            paren.add("(" + s + ")");
        }

        ArrayList<String> arr = new ArrayList<>(paren);
        Collections.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(5));
    }
}
