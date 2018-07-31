import java.util.Arrays;

public class RemoveDuplicatesFromArray {
    public static int[] removeDuplicates(int[] nums) {
        int length = nums.length;
        if (length == 1) {
            return nums;
            // return 1;
        }
        int left = 0;
        int right = 1;
        boolean dup = false;
        while (right < length) {
            dup = false; //reset
            while (right < length && nums[left] < nums[right]) {
                left++;
                right++;
            }
            while (right < length && nums[left] == nums[right]) {
                right++;
                dup = true;
            }
            if (right < length && dup) {
                nums[left++] = nums[right++];
            }
        }
        //return left;
        return Arrays.copyOf(nums, left);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(removeDuplicates(new int[]{1,1,2,2,3,3,4})));
    }
}
