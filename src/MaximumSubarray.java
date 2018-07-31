public class MaximumSubarray {

    public static int maxSubArray(int[] nums) {
        return maxSub(nums,0,nums.length-1);
    }

    private static int maxSub(int[] nums, int low, int high) {
        //base case
        if(low == high) return nums[low];
        int mid = (low + high) / 2;
        System.out.printf("[low=%d high=%d]mid=%d\n",low,high,mid);
        int maxsubleft = maxSub(nums,low,mid);
        System.out.printf("[low=%d high=%d]maxsubleft=%d\n",low,high,maxsubleft);
        int maxsubright = maxSub(nums,mid+1,high);
        System.out.printf("[low=%d high=%d]maxsubright=%d\n",low,high,maxsubright);
        int maxcross = maxCross(nums,low,mid,high);
        System.out.printf("[low=%d high=%d]maxcross=%d\n",low,high,maxcross);

        return Math.max(Math.max(maxsubleft, maxsubright), maxcross);
    }

    private static int maxCross(int[] nums, int low, int mid, int high) {
        int leftmax = Integer.MIN_VALUE;
        int sumleft = 0;
        for(int i=mid;i>=low;i--) {
            sumleft += nums[i];
            leftmax = Math.max(leftmax, sumleft);
        }
        System.out.printf("[low=%d high=%d]leftmax=%d\n",low,high,leftmax);
        int rightmax = Integer.MIN_VALUE;
        int sumright = 0;
        for(int i=mid+1;i<=high;i++) {
            sumright += nums[i];
            rightmax = Math.max(rightmax, sumright);
        }
        System.out.printf("[low=%d high=%d]rightmax=%d\n",low,high,rightmax);
        return leftmax + rightmax;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));

    }
}
