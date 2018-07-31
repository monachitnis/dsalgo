import java.util.ArrayList;
import java.util.Arrays;

public class PainterPartition {
    private static int maxTimePossible(ArrayList<Integer> C, int B) {
        long time = 0;
        for (Integer aC : C) {
            time += ((long) aC * B) % 10000003; //overflow handling
        }
        return (int) time;
    }

    private static boolean isPossible(int time, ArrayList<Integer> C, int painters, int B) {
        // whole C should be partition-able with n(painters) partitions, with sum <= time
        int index = 0;
        long sum = 0;
        while (painters > 0) {
            while (index < C.size() && sum <= time) {
                sum += (C.get(index) * (long) B) % 10000003;
                index++;
            }
            if (sum > time) {
                //start new partition
                index--;
                painters--;
                sum = 0;
            } else if (index == C.size()) { // reached last partition
                painters--;
            }
        }
        return index >= C.size();
    }

    public static int paintPartition(int A, int B, ArrayList<Integer> C) {
        //A = painters
        //B = time to paint 1 unit
        int low = 0;
        int high = maxTimePossible(C, B);
        int mid = 0;
        int lastPoss = mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (isPossible(mid, C, A, B)) {
                lastPoss = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return lastPoss;
    }

    public static void main(String[] args) {
        System.out.println(paintPartition(1, 1000000, new ArrayList<>(Arrays.asList(1000000, 1000000))));

    }
}
