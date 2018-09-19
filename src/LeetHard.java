import java.util.*;

class LeetHard {
    public static List<int[]> getSkyline(int[][] buildings) {
        List<int[]> coordinates = new ArrayList<>();

        // corner cases
        if( buildings.length == 0) return coordinates;
        if (buildings.length == 1) {
            coordinates.add(new int[]{buildings[0][0], buildings[0][2]});
            coordinates.add(new int[]{buildings[0][1], 0});
            return coordinates;
        }

        // strategy is to go incrementally on X-Axis

        PriorityQueue<Building> maxHeight = new PriorityQueue<>(buildings.length, new BuildingComparator());
        TreeMap<Integer, ArrayList<Building>> start = new TreeMap<>();
        TreeMap<Integer, ArrayList<Building>> end = new TreeMap<>();

        int leftBorder = 0;
        int rightBorder = 1;
        initArray(buildings, start, leftBorder);
        initArray(buildings, end, rightBorder);

        int i = 0;
        int lastMax = -1;
        while(i <= Math.max(start.lastKey(), end.lastKey())) {
            boolean changed = false;

            if(end.containsKey(i)) {
                changed = true;
                for (Building b : end.get(i)) {
                    maxHeight.remove(b);
                }
            }

            if(start.containsKey(i)) {
                changed = true;
                maxHeight.addAll(start.get(i));
            }

            // peek in priority Q should give the highest height building first
            if(!maxHeight.isEmpty() && maxHeight.peek().hi == lastMax) {
                changed = false;
            }

            // add coordinate
            if(changed) {
                if (!maxHeight.isEmpty()) {
                    lastMax = maxHeight.peek().hi;
                    coordinates.add(new int[]{i, lastMax});
                } else {
                    coordinates.add(new int[]{i, 0});
                }
            }

            int minKey = Integer.MAX_VALUE;

            if(start.higherKey(i) != null) {
                minKey = start.higherKey(i);
            }
            if (end.higherKey(i) != null) {
                minKey = Math.min(minKey, end.higherKey(i));
            }
            if (i == minKey) break;
            // else infinite loop since while loop on i <=
            i = minKey;
        }


        return coordinates;
    }

    private static void initArray(int[][] buildings, TreeMap<Integer, ArrayList<Building>> arr, int index) {
        //[2 9 10]
        // use building.li as index
        for(int[] building : buildings) {
            ArrayList<Building> ab = arr.getOrDefault(building[index], new ArrayList<>());
            ab.add(new Building(building));
            arr.put(building[index], ab);
        }
    }

    private static class Building {
        int li;
        int ri;
        int hi;

        Building(int[] points) {
            li = points[0];
            ri = points[1];
            hi = points[2];
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Building) {
                Building that = (Building)obj;
                return this.li == that.li && this.ri == that.ri && this.hi == that.hi;
            }
            return false;
        }
    }
    private static class BuildingComparator implements Comparator<Building> {

        @Override
        public int compare(Building o1, Building o2) {
            // reverse by height ordering
            // i.e. higher building comes first when peeking with priority Q
            return o2.hi - o1.hi;
        }
    }
}
