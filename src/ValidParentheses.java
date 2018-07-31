public class ValidParentheses {

    public static boolean isValidParentheses(String s) {

        DS.StackX stackx = new DS.StackX();

        // push opening brackets '(', '[', '{'
        // and pop on closing brackets ')', ']', '}'
        // check corresponding popped matches type for current
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    stackx.push(c);
                    break;
                case ')':
                case ']':
                case '}':
                    char popped = stackx.pop();
                    if (c == ')' && popped != '(')
                        return false;
                    if (c == ']' && popped != '[')
                        return false;
                    if (c == '}' && popped != '{')
                        return false;
            }
        }
        return stackx.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValidParentheses("()[]{}"));
    }
}
