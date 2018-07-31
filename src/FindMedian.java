import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FindMedian {
    // brute-forcey to visit all k=M*N/2 elems to find elem less than k other elems
    // minor optimization to skip rows, where all elems have been visited - colptr
    public static int findMedian1(ArrayList<ArrayList<Integer>> A) {
        int N = A.size();
        int M = A.get(0).size();
        if (A.size() == 1) {
            return A.get(0).get(M / 2);
        }
        int medianPos = N * M / 2;
        int visited = 0;
        int[] colptr = new int[N];
        int min = 0;
        while (visited <= medianPos) {
            min = Integer.MAX_VALUE;
            int minrow = 0;
            // get all rows' elems at colptr
            for (int r = 0; r < N; r++) {
                if (colptr[r] >= M) {
                    continue;
                }
                if (A.get(r).get(colptr[r]) < min) {
                    min = A.get(r).get(colptr[r]);
                    minrow = r;
                }
            }
            colptr[minrow] = colptr[minrow] + 1;
            visited++;
        }
        return min;
    }

    // recursively adjust the median candidate itself (not array ranges), until lessthan == k (median position)
    public static int findMedian(ArrayList<ArrayList<Integer>> A) {
        int N = A.size();
        int M = A.get(0).size();
        if (A.size() == 1) {
            return A.get(0).get(M / 2);
        }
        int k = N * M / 2;
        // once we find elem that > n(k) elems, we've found median
        // i.e. find (k+1)th smallest elem
        // apply binary search
        int rk = M / 2; //row median pos
        int low = 1;
        int high = Integer.MAX_VALUE - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            int lessthan = 0;
            for (ArrayList<Integer> aA : A) {
                //int rowmin = aA.get(0);
                int rowmax = aA.get(M - 1);
                int rowmed = aA.get(rk);
                if (mid >= rowmax) lessthan += M;
                else if (mid >= rowmed) lessthan += Math.max(M / 2, 1); //account for M=1
            }
            if (lessthan == k) {
                return mid;
            }
            if (lessthan > k) high = mid - 1;
            else low = mid + 1;
        }
        return mid;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int l = (m + n + 1) / 2;
        int r = (m + n + 2) / 2;
        return (kth(nums1, 0, nums2, 0, l) + kth(nums1, 0, nums2, 0, r)) / 2.0;
    }

    //overall median k is based on both A and B's length, so adjust each boundary defensively but still logarithmically by k/2
    private static double kth(int[] A, int startA, int[] B, int startB, int k) {
        if (startA > A.length - 1) return B[startB + k - 1];
        if (startB > B.length - 1) return A[startA + k - 1];
        if (k == 1) return Math.min(A[startA], B[startB]);

        int midA = Integer.MAX_VALUE;
        int midB = Integer.MAX_VALUE;
        if (startA + k / 2 - 1 < A.length) midA = A[startA + k / 2 - 1];
        if (startB + k / 2 - 1 < B.length) midB = B[startB + k / 2 - 1];

        if (midA < midB) {
            return kth(A, startA + k / 2, B, startB, k / 2);
        } else {
            return kth(A, startA, B, startB + k / 2, k / 2);
        }
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
        matrix.add(new ArrayList<>(Collections.singletonList(2)));
        matrix.add(new ArrayList<>(Collections.singletonList(1)));
        matrix.add(new ArrayList<>(Collections.singletonList(4)));
        matrix.add(new ArrayList<>(Collections.singletonList(1)));
        matrix.add(new ArrayList<>(Collections.singletonList(2)));
        matrix.add(new ArrayList<>(Collections.singletonList(2)));
        matrix.add(new ArrayList<>(Collections.singletonList(5)));
        matrix.add(new ArrayList<>(Arrays.asList(1,3,5)));
        matrix.add(new ArrayList<>(Arrays.asList(2,6,9)));
        matrix.add(new ArrayList<>(Arrays.asList(3,6,9)));
        matrix.add(new ArrayList<>(Arrays.asList(1,3,5,15,16)));
        matrix.add(new ArrayList<>(Arrays.asList(2,6,8,9,10)));
        matrix.add(new ArrayList<>(Arrays.asList(17,18,20,30,100)));
        //1,2,3,5,6,8,9,9,15,16,17,18,20,30,100
        System.out.println(findMedian(matrix));
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // trivial cases
        if (m == 0 && n == 0) return 0;
        if (m == 0) {
            boolean isOdd = n % 2 != 0;
            if (isOdd) return nums2[n / 2] * 1.0;
            else return (nums2[n / 2] + nums2[n / 2 - 1]) / 2.0;
        }
        if (n == 0) {
            boolean isOdd = m % 2 != 0;
            if (isOdd) return nums1[m / 2] * 1.0;
            else return (nums1[m / 2] + nums1[m / 2 - 1]) / 2.0;
        }
        int low1 = 0;
        int high1 = m - 1;
        int low2 = 0;
        int high2 = n - 1;
        while (low1 <= high1 && low2 <= high2) {
            int mid1 = (low1 + high1) / 2;
            int mid2 = (low2 + high2) / 2;
            int nummid1 = nums1[mid1];
            int nummid2 = nums2[mid2];
            if (nummid1 <= nummid2) {
                low1 = mid1 + 1;
                high2 = mid2 - 1;
            } else {
                low2 = mid2 + 1;
                high1 = mid1 - 1;
            }
        }
        boolean isOdd = (m + n) % 2 != 0;
        int l1 = Math.min(low1, m - 1);
        int l2 = Math.min(low2, n - 1);
        int lower = nums1[l1] < nums2[l2] ? nums1[l1] : nums2[l2];
        if (isOdd) {
            return lower * 1.0;
        } else {
            return (nums1[l1] + nums2[l2]) / 2.0;
        }
    }
}
