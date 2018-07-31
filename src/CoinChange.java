import java.util.*;
import java.util.stream.Collectors;

public class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        // edges cases
        if (amount == 0) return 0;
        int n = coins.length;
        if (n == 0) return -1;

        int[] table = new int[amount + 1];
        table[0] = 0;
        for (int i = 1; i < table.length; i++) {
            table[i] = Integer.MAX_VALUE - 1;
        }
        for (int c : coins) {
            for (int i = 1; i <= amount; i++) {
                if (c <= i) {
                    table[i] = Math.min(table[i], table[i - c] + 1);
                }
            }
        }
        return table[amount] == Integer.MAX_VALUE - 1 ? -1 : table[amount];


    }

    public static int coinChange2(int[] coins, int amount) {
        Map<Integer, Integer> memoize = new HashMap<>(amount);

        List<Integer> coin = Arrays.stream(coins).boxed().collect(Collectors.toList());
        Collections.sort(coin);

        return helper(memoize, coin, amount);

    }

    private static int helper(Map<Integer, Integer> cache, List<Integer> coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (coins.get(0) > amount) { // smallest denomination is greater than target
            return -1;
        }
        int min = Integer.MAX_VALUE;
        int count = 0;
        int rem = amount;
        for (int i = coins.size() - 1; i >= 0; i--) {
            if (cache.containsKey(rem)) {
                count = cache.get(rem);
                min = Math.min(min, count);
                break;
            } else {
                int n = rem / coins.get(i);
                if (n > 0) {
                    rem -= coins.get(i) * n;
                    if (rem == 0) {
                        count += n;
                        min = Math.min(min, count);
                        cache.put(amount, count);
                        break;
                    } else if (rem < coins.get(i) && i == 0) { //bad path
                        return -1;
                    } else {
                        count += n;
                        continue;
                    }
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{186,419,83,408}, 6249)); //83,186,408,419
        System.out.println(coinChange(new int[]{1,2}, 3));
    }
}
