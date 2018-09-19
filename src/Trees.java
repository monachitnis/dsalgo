import java.util.*;
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

    public static List<DS.TreeNode> findDuplicateSubtrees(DS.TreeNode root) {
        Set<DS.TreeNode> dup = new HashSet<>();
        //corner case
        if (root != null) {
            DS.TreeNode left = root.left;
            DS.TreeNode right = root.right;
            while(left != null && right != null) {
                if(eq(left, right, dup)) {
                    //addNonNull(dup, left);
                } else if(eq(left, right.left, dup)) {
                    //addNonNull(dup, left);
                } else if(eq(left.right, right, dup)) {
                    //addNonNull(dup, left);
                }
                left = left.left;
                right = right.right;
            }
        }
        return new ArrayList<>(dup);
    }

    // top-down alternate solution without passing dups to recursive method
    private static boolean eq(DS.TreeNode n1, DS.TreeNode n2, Set<DS.TreeNode> dups) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null && n2 != null) return false;
        if (n1 != null && n2 == null) return false;
        if (n1.val != n2.val) return false;
        if (eq(n1.left, n2.left, dups) && eq(n1.right, n2.right, dups)) {
            dups.add(n1);
            return true;
        }
        return false;
    }

    public static int kthSmallest(DS.TreeNode root, int k) {
        int[] kth = new int[1];
        kth[0] = k;
        return helper(root, kth);
    }

    private static int helper(DS.TreeNode root, int[] k) {
        // base case
        if (root == null) return -1;
        int leftRes = helper(root.left, k);
        k[0] = Math.max(0, k[0] - 1);
        if (leftRes < 0) {
            if (k[0] == 0) return root.val;
            else {
                int rightRes = helper(root.right, k);
                if (rightRes > 0) {
                    k[0] = Math.max(0, k[0] - 1);
                }
                if (k[0] == 0) return rightRes;
                else return leftRes;
            }
        } else {
            return leftRes;
        }
    }


    public static void main(String[] args) {
        DS.TreeNode tree = DS.TreeNode.createTree();
        System.out.println(isBalanced(tree));

        System.out.println(rightSideView(createTree()));

        DS.TreeNode tree1 = TreeSerDe.deserialize("[5,3,6,2,4,null,null,1]");
        System.out.println(kthSmallest(tree1, 1));
        System.out.println(kthSmallest(tree1, 2));
        System.out.println(kthSmallest(tree1, 3));
        System.out.println(kthSmallest(tree1, 4));
        System.out.println(kthSmallest(tree1, 5));
        System.out.println(kthSmallest(tree1, 6));
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
