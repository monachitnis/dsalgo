import java.util.*;

public class Worksheet {

    public static void main(String[] args) {

        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(20, 1, 3, 21));
        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(4,5,6,7));
        Set<ArrayList<Integer>> myset = new HashSet<>();
        myset.add(list1);
        myset.add(list2);
        System.out.println(myset);
        ArrayList<ArrayList<Integer>> list3 = new ArrayList<>(myset);
        System.out.println(list3);
        ArrayList<Integer> list2a = (ArrayList<Integer>) list1.clone();
        Collections.sort(list2a);
        System.out.println(list1);

        // Test Servers allocate/deallocate
        DS.Servers s1 = new DS.Servers();
        System.out.println(s1.allocate("apibox"));
        System.out.println(s1.allocate("apibox"));
        System.out.println(s1.deallocate("apibox2"));
        System.out.println(s1.deallocate("apibox"));

        // test various array printing
        Integer[] arr = new Integer[]{11,13,14};
        System.out.println(Arrays.toString(arr));
        ArrayList<Integer> ai = new ArrayList<>();
        ai.add(1);
        ai.add(1);
        ai.add(1);
        ai.add(1);
        System.out.println(ai);
        ArrayList<Integer> ai2 = new ArrayList<>(Arrays.asList(arr));
        System.out.println(ai2);
        Set<Integer> set1 = new HashSet<>(ai);
        System.out.println(set1);
        // combining two arrays
        // Time O(N) and space O(N) approach using new array of total length and assigning
        // Maybe more efficient approach by converting to Arraylists and using native (C++) implementations of addAll
        // - but little worse space complexity and young-gen ED for locally inited arraylists
        Integer[] arr2 = new Integer[]{1,3,4};
        if(arr[arr.length-1] < arr2[0]) {
            ArrayList<Integer> concat = ai2;
            concat.addAll(Arrays.asList(arr2));
            System.out.println(concat);
        } else if(arr2[arr2.length-1] < arr[0]) {
            ArrayList<Integer> concat = new ArrayList<>(Arrays.asList(arr2));
            concat.addAll(ai2);
            System.out.println(concat);
        }

        //BITWISE OPS
        //XOR
        System.out.println(4 ^ 7);

        //strStr()
        String x = "abcabc";
        boolean e = x.charAt(0) == x.charAt(4);
        System.out.println(e);
    }
}