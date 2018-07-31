import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaterTrap {
    public static int maxArea(int[] height) {
        int volMax = Integer.MIN_VALUE;
        int n = height.length;
        int width = 0;
        int[][] obstacles = new int[n][n];
        for (int i = 0; i < n; i++) {
            int vol = 0;
            int ob = 0;
            for (int j = i + 1; j < n; j++) {
                width = j - i;
                //System.out.println("width="+width);
                if (obstacles[i][j] > 0) {
                    ob = obstacles[i][j];
                } else {
                    for (int x = j - 1; x > i; x--) {
                        ob += height[x];
                    }
                    obstacles[i][j] = ob;
                    //System.out.println("obs="+ob);
                }
                vol = width * Math.min(height[i], height[j]) - ob;
                //System.out.println("vol="+vol);
            }
            volMax = Math.max(volMax, vol);
        }
        return volMax;
    }

    /**
     * Stuck in local minima, undercounts a 'W' like shape.
     * To solve - maintain maxRight1 and keep searching maxRight2 if it can exceed Left
     */
    public static int trap(final List<Integer> A) {
        //corner case
        if (A.size() <= 2) return 0;
        int left = 0;
        int right = 1;
        int water = 0;
        while (right < A.size()) {
            while (notWall(left, A)) {
                left++;
                System.out.println("Moving left wall to " + A.get(left));
            }
            right = left + 1;
            if (right >= A.size()) break;
            System.out.println("Moving right wall to " + A.get(right));
            while (notWall(right, A) && A.get(right) < A.get(left)) {
                right++;
                System.out.println("Moving right wall to " + A.get(right));
            }
            if (right >= A.size()) break;
            // keep searching for higher right - bigger water trap
            int right2 = right;
            //int curMax = A.get(left);
            while (right2 < A.size() && A.get(right2) < A.get(left)) {
                right2++;
                /*if (A.get(right2) < curMax) right2++;
                else {
                    curMax = Math.max(A.get(right2), curMax);
                    right = right2;
                    right2++;
                }*/
            }
            if (right2 < A.size()) right = right2; //else, no harm done
            // flow water from left and right
            int w = 0;
            for (int i = left + 1; i < right; i++) {
                int flowFrom = Math.min(A.get(left), A.get(right));
                if (flowFrom - A.get(i) > 0) w += flowFrom - A.get(i);
            }
            System.out.println("water=" + w + " between left:" + A.get(left) + ",right:" + A.get(right));
            left = right;
            System.out.println("Moving left wall to " + A.get(left));
            water += w;
        }
        return water;
    }

    private static boolean notWall(int i, final List<Integer> A) {
        boolean xleft = i == 0 && A.get(i) > 0;
        boolean xright = i == A.size() - 1 && A.get(i) > 0;
        boolean midwall = i > 0 && i < A.size() - 1 && A.get(i - 1) < A.get(i) && A.get(i + 1) < A.get(i);
        return !xleft && !xright && !midwall;
    }

    public static void main(String[] args) {
        List<Integer> trap1 = new ArrayList<>(Arrays.asList(56, 6, 52, 43, 23, 47, 48, 38, 96, 46, 30, 66, 80, 15,
                62, 71, 61, 12, 98, 9, 28, 81, 70, 59, 95, 34, 9, 60, 70, 81, 71, 67, 58, 20, 22, 3, 95, 85, 20, 24,
                74, 5, 23, 33, 75, 50, 38, 75, 68, 26, 46, 30, 75, 18, 4, 42, 51, 59, 8, 77));
        System.out.println(trap(trap1));
    }
}
