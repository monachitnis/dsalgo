import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NextGreater {
    public static int[] nextGreaterElements(int[] nums) {
        int[] greater = new int[nums.length];
        Map<Integer, Integer> g = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            int next = -1;
            if (g.containsKey(nums[i])) {
                next = g.get(nums[i]);
            } else {
                next = helper(nums, i);
                g.put(nums[i], next);
            }
            greater[i] = next;
        }
        return greater;
    }
    private static int helper(int[] nums, int index) {
        //given index, search in nums for index of next in order
        int elem = nums[index];
        int orig = index;
        boolean wrapped = false;
        index++;
        if (index == nums.length) {
            //wrap-around
            index=0;
            wrapped = true;
        }
        while(nums[index] < elem) {
            System.out.println("Comparing " + elem + " and " + nums[index]);
            index++;
            if (index == nums.length) {
                if(!wrapped) {
                    //wrap-around
                    index = 0;
                    if (index == orig) return -1;
                    wrapped = true;
                } else return -1;
            }
        }
        if (wrapped && index == orig) return -1;
        return nums[index];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{5,4,3,2,1})));

    }
}
