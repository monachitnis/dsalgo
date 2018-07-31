import java.util.*;

public class Combinations {
    private static ArrayList<ArrayList<Integer>> helper(ArrayList<Integer> a, ArrayList<Integer> acc) {
        //System.out.println("a=" + a);
        //System.out.println("acc=" + acc);
        ArrayList<ArrayList<Integer>> acced = new ArrayList<>();
        if (a.isEmpty()) {
            acced.add(acc);
        } else {
            Integer head = a.remove(0);
            ArrayList<Integer> tail = a;
            ArrayList<Integer> accWithHead = (ArrayList<Integer>) acc.clone();
            accWithHead.add(0, head);
            //System.out.println("a=" + a);
            //System.out.println("accCopy=" + accWithHead);
            ArrayList<ArrayList<Integer>> combosWithHead = helper(tail, accWithHead);
            ArrayList<ArrayList<Integer>> combosWithoutHead = helper(tail, acc);
            acced.addAll(combosWithHead);
            acced.addAll(combosWithoutHead);
        }
        return acced;
    }

    public static ArrayList<ArrayList<Integer>> allCombinations(ArrayList<Integer> a) {
        ArrayList<ArrayList<Integer>> acced = helper(a, new ArrayList<>());
        acced.removeIf(ArrayList::isEmpty);
        return acced;
    }

    public static Set<ArrayList<Integer>> combinations(ArrayList<Integer> group, int k) {

        Set<ArrayList<Integer>> allCombos = new HashSet<>();
        // base cases for recursion
        if (k == 0) {
            // There is only one combination of size 0, the empty set.
            allCombos.add(new ArrayList<>());
            return allCombos;
        }
        if (k > group.size()) {
            return allCombos;
        }

        // Create a copy of the group with one item removed.
        ArrayList<Integer> groupWithoutX = new ArrayList<>(group);
        Integer x = groupWithoutX.remove(groupWithoutX.size() - 1);

        Set<ArrayList<Integer>> combosWithoutX = combinations(groupWithoutX, k);
        Set<ArrayList<Integer>> combosWithX = combinations(groupWithoutX, k - 1);
        for (List<Integer> combo : combosWithX) {
            combo.add(x);
        }
        allCombos.addAll(combosWithoutX);
        allCombos.addAll(combosWithX);
        return allCombos;
    }

    private static boolean isSum(List<Integer> set, int b) {
        int sum = 0;
        for (int s : set) {
            sum += s;
        }
        return sum == b;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(15, 8, 15, 10, 19, 18, 10, 3, 11, 7, 17));
        int b = 33;
        //System.out.println(s.allCombinations(a));
        Set<ArrayList<Integer>> combs = new HashSet<>();
        for(int i = a.size(); i > 0; i--) {
            Set<ArrayList<Integer>> comb = combinations(a, i);
            for (ArrayList<Integer> c: comb) {
                if (isSum(c, b)) {
                    Collections.sort(c);
                    combs.add(c);
                }
            }
        }
        ArrayList<ArrayList<Integer>> possible = new ArrayList<>(combs);
        System.out.println(possible);
        //[3 7 8 15 ] [3 11 19 ] [3 15 15 ] [7 8 18 ] [7 11 15 ] [8 10 15 ] [15 18 ]
    }
}
