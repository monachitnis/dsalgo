public class BalanceTree {

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

    public static void main(String[] args) {
        DS.TreeNode tree = DS.TreeNode.createTree();
        System.out.println(isBalanced(tree));
    }
}
