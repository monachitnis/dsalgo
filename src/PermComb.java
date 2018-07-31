import java.util.ArrayList;

public class PermComb {

    private static int size;
    private static int count = 0;
    private static char[] arrChar = new char[100];

    public static void main(String[] args) {
        // 1. given list of characters, return all combinations
        ArrayList<String> input1 = new ArrayList<>();
        input1.add("a");
        input1.add("b");
        input1.add("c");
        input1.add("d");
        ArrayList<ArrayList<String>> allcombs = getAllCombinations(input1);
        for (ArrayList<String> allcomb : allcombs) {
            System.out.println(allcomb);
        }

        // 2. anagrams aka permutations of a string
        String input = "cats";
        size = input.length();
        for (int i = 0; i < size; i++) {
            arrChar[i] = input.charAt(i);
        }
        doAnagram(size);

    }

    private static void doAnagram(int newsize) {
        //base case
        if (newsize == 1) {
            displayWord();
            return;
        }
        for (int i = 0; i < newsize; i++) {
            doAnagram(newsize - 1);
            rotate(newsize);
        }
    }

    private static void rotate(int newsize) {
        int position = size - newsize;
        int j;
        char temp = arrChar[position];
        for (j = position; j < size - 1; j++)
            arrChar[j] = arrChar[j + 1];
        arrChar[j] = temp;
    }

    private static void displayWord() {
        if (count < 99) System.out.print(" ");
        if (count < 9) System.out.print(" ");
        System.out.print(++count + " ");
        for (int j = 0; j < size; j++)
            System.out.print(arrChar[j]);
        System.out.print("  ");
        System.out.flush();
        if (count % 6 == 0)
            System.out.println(" ");
    }

    private static ArrayList<ArrayList<String>> getAllCombinations(ArrayList<String> arr) {
        return helper(arr, new ArrayList<>());
    }

    private static ArrayList<ArrayList<String>> helper(ArrayList<String> sublist, ArrayList<String> acc) {
        if (sublist.isEmpty()) {
            ArrayList<ArrayList<String>> ret = new ArrayList<>();
            ret.add(acc);
            return ret;
        }
        String head = sublist.get(0);
        ArrayList<String> tail = new ArrayList<>(sublist);
        tail.remove(0);
        ArrayList<String> accHead = new ArrayList<>(acc);
        accHead.add(head);

        ArrayList<ArrayList<String>> recHead = helper(tail, accHead);
        ArrayList<ArrayList<String>> recTail = helper(tail, acc);
        recHead.addAll(recTail);
        return recHead;
    }
}
