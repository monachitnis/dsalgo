import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TakeCourses {

    private static int rem;

    private static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Node> vx = new HashMap<>();
        rem = numCourses;
        for (int[] p : prerequisites) {
            int source = p[0];
            int dest = p[1];
            Node srcNode = vx.get(source);
            if (srcNode == null) {
                Node n = new Node(source);
                vx.put(source, n);
            }
            Node dstNode = vx.get(dest);
            if (dstNode == null) {
                Node n = new Node(dest);
                vx.put(dest, n);
            }
            vx.get(source).addNeigh(vx.get(dest));
        }
        for (Node n : vx.values()) {
            if (!dfs(n)) return false;
        }
        return true;
    }

    private static boolean dfs(Node s) {
        s.visited = true;
        for (Node n : s.neigh) {
            if (!n.visited) {
                dfs(n);
            }
        }
        if (s.rank > 0) {
            //has been visited and assigned rank before in forward direction
            //now re-visited again in backward stack pop
            //therefore-loop
            return false;
        }
        s.rank = rem;
        rem--;
        return true;
    }

    private static class Node {
        int val;
        List<Node> neigh;
        boolean visited;
        boolean done;
        int rank;

        Node(int v) {
            this.val = v;
            this.neigh = new ArrayList<>();
            this.visited = false;
            this.rank = -1;
        }

        void addNeigh(int v) {
            neigh.add(new Node(v));
        }

        void addNeigh(Node n) {
            neigh.add(n);
        }
    }

   public static void main(String[] args) {
        int[][] edges = new int[1][2];
        edges[0] = new int[]{0,1};
        //edges[1] = new int[]{1,0};
        System.out.println(canFinish(2, edges));
    }
}
