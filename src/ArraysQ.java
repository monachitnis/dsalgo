import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Your KthLargest object will be instantiated and called as such:
     * KthLargest obj = new KthLargest(k, nums);
     * int param_1 = obj.add(val);
     * e.g.
     * int k = 3;
     * int[] arr = [4,5,8,2];
     * KthLargest kthLargest = new KthLargest(3, arr);
     * kthLargest.add(3);   // returns 4
     * kthLargest.add(5);   // returns 5
     * kthLargest.add(10);  // returns 5
     * kthLargest.add(9);   // returns 8
     * kthLargest.add(4);   // returns 8
     */
    class KthLargest {
        int[] karr;
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        int count = 0;

        public KthLargest(int k, int[] nums) {
            karr = new int[k];
            for(int i = 0; i < Math.min(k,nums.length); i++) {
                karr[i] = nums[i];
                count++;
                if(nums[i] < min) {
                    min = nums[i];
                    minIndex = i;
                }
            }
            if (nums.length > k) {
                for(int i = k; i < nums.length; i++) {
                    replaceMin(nums[i]);
                }
            }
            //Trick: min in k-length list is k-th largest overall
        }

        public int add(int val) {
            if(count == karr.length) {
                replaceMin(val);
            } else {
                //simply add first and just update min
                karr[count] = val;
                if(val < min) {
                    min = val;
                    minIndex = count;
                }
                count++;
            }
            return min;
        }

        private void replaceMin(int val) {
            if(val > min) {
                // replace with this greater element
                karr[minIndex] = val;
                // but now find new min
                min = Integer.MAX_VALUE;
                for(int j = 0; j < karr.length; j++) {
                    if(karr[j] < min) {
                        min = karr[j];
                        minIndex = j;
                    }
                }
            }
        }
    }
}
