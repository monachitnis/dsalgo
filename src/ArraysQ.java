import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArraysQ {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findErrorNums(new int[]{2, 3, 3, 6, 7, 5, 4, 8, 9})));
    }

    static int[] findErrorNums(int[] nums) {
        Set<Integer> numset = new HashSet<>(nums.length);
        int missing = -1;
        int repeat = -1;
        for(int num : nums) {
            if(numset.contains(num)) {
                repeat = num;
            } else {
                numset.add(num);
            }
        }
        for(int i = 1; i <= nums.length; i++) {
            if(!numset.contains(i)) {
                missing = i;
                break;
            }
        }
        return new int[]{repeat, missing};
    }
}
