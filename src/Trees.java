import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class Trees {

    public static boolean isBalanced(DS.TreeNode root) {
        Subtree s = depth(root);
        return s.balanced && (Math.abs(s.depth[0] - s.depth[1]) <= 1);
    }

    public static Subtree depth(DS.TreeNode root) {
        //base case
        if (root == null) return new Subtree();
        int[] d = new int[2];
        Subtree left = depth(root.left);
        d[0] = 1 + Math.max(left.depth[0], left.depth[1]);
        Subtree right = depth(root.right);
        d[1] = 1 + Math.max(right.depth[0], right.depth[1]);
        boolean balanced = left.balanced && right.balanced && Math.abs(d[0] - d[1]) <= 1;
        return new Subtree(d, balanced);
    }

    static class Subtree {
        int[] depth;
        boolean balanced;

        Subtree(int[] d, boolean b) {
            this.depth = d;
            this.balanced = b;
        }

        Subtree() {
            this.depth = new int[2];
            this.balanced = true;
        }
    }

    public static List<Integer> rightSideView(DS.TreeNode root) {
        // BFS traversal
        // compare !Q.contains(ret-list.last-added)
        if(root == null) return new ArrayList<>();
        List<DS.TreeNode> ret = new ArrayList<>();
        LinkedList<DS.TreeNode> q = new LinkedList<>();
        q.add(root);
        ret.add(root);
        while(!q.isEmpty()) {
            DS.TreeNode head = q.remove();
            if (head.left != null) {
                q.add(head.left);
            }
            if (head.right != null) {
                q.add(head.right);
            }
            if (!q.isEmpty() && !q.contains(ret.get(ret.size()-1))) {
                ret.add(q.getLast());
            }
        }
        return ret.stream().map(node -> node.val).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        DS.TreeNode tree = DS.TreeNode.createTree();
        System.out.println(isBalanced(tree));

        System.out.println(rightSideView(createTree()));
    }

    static DS.TreeNode createTree() {
        DS.TreeNode A = new DS.TreeNode(1);
        A.left = new DS.TreeNode(2);
        A.right = new DS.TreeNode(3);
        A.left.right = new DS.TreeNode(5);
        A.right.right = new DS.TreeNode(4);


        return A;
    }
}
