import java.util.Arrays;
import java.util.Objects;

public class Trie {
    char letter;
    boolean isWord;
    Trie[] children;

    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26]; // only initialize array, not all children
        letter = '0'; //dummy root letter
        isWord = false;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie head = this;
        for (int l = 0; l < word.length(); l++) {
            // map letter to index by letter - 'a'
            int index = word.charAt(l) - 'a';
            if (head.children[index] != null) {
                head = head.children[index];
            } else {
                Trie child = new Trie();
                child.letter = word.charAt(l);
                head.children[index] = child;
                head = child;
            }
            if (l == word.length() - 1) {
                // last letter, mark word
                head.isWord = true;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return prefixSearch(word, false);
    }

    private boolean prefixSearch(String word, boolean prefixMatch) {
        char[] letters = word.toCharArray();
        Trie head = this;
        for(char l : letters) {
            // map letter to index by letter - 'a'
            int index = l - 'a';
            if (head.children[index] == null) {
                return false;
            }
            head = head.children[index];
        }
        char lastLetter = letters[letters.length-1];
        return prefixMatch || (head.letter == lastLetter && head.isWord);
    }

    /*private boolean branchEnd(Trie t) {
        for(Trie child : t.children) {
            if (child != null) return false;
        }
        return true;
    }*/

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return prefixSearch(prefix, true);
    }

    public static void main(String[] args) {
        System.out.println('b' - 'a');
        Trie obj = new Trie();
        obj.insert("apple");
        System.out.println(obj.search("apple")); // true
        System.out.println(obj.search("app")); // false
        System.out.println(obj.startsWith("app")); // true
        obj.insert("app");
        System.out.println(obj.search("app")); // true
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


class WordDictionary {
    Trie dict;

    /** Initialize your data structure here. */
    public WordDictionary() {
        dict = new Trie();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        dict.insert(word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(dict, word);
    }

    private boolean search(Trie t, String word) {
        if (t == null) return false;
        Trie head = t;
        // if dot, you have to search all children for word following dot
        if(!word.contains(".")) {
            return head.search(word);
        } else if(word.endsWith(".")) {
            // get prefix
            for(int lastdot = word.length()-2; lastdot >= 0; lastdot--) {
                if(word.charAt(lastdot) != '.') {
                    return head.startsWith(word.substring(0, lastdot+1));
                }
            }
        } else {
            for(int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == '.') {
                    // move one step, search non-null children for next substring
                    Trie[] nn = Arrays.stream(head.children).filter(Objects::nonNull).toArray(Trie[]::new);
                    boolean match = false;
                    for (Trie child : nn) {
                        match = match || search(child, word.substring(i + 1));
                    }
                    return match;
                } else {
                    int index = word.charAt(i) - 'a';
                    if (head.children[index] == null) {
                        return false;
                    }
                    head = head.children[index];
                }
            }
        }
        char lastLetter = word.charAt(word.length()-1);
        return (head.letter == lastLetter && head.isWord);
    }

    public static void main(String[] args) {
        WordDictionary w = new WordDictionary();
        w.addWord("bad");
        w.addWord("dad");
        w.addWord("mad");
        w.addWord("mac");
        System.out.println(w.search("pad")); // false
        System.out.println(w.search("bad")); // true
        System.out.println(w.search("b..")); // true
        System.out.println(w.search(".ad")); // true
        System.out.println(w.search("m.x")); // true
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */