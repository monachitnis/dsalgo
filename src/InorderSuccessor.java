public class InorderSuccessor {
    public static String inorder(DS.TreeNode node) {
        //base case
        if(node == null) return "";
        return inorder(node.left) + "-" + node.val + "-" + inorder(node.right);
    }

    public static DS.TreeNode inorderSuccessor(DS.TreeNode root, DS.TreeNode x) {

        if (root != null) {
            // x is left child
            if (root.left != null && root.left.val == x.val) return root;
            // x is root --> find smallest in right subtree
            if (root.val == x.val) return leftMost(root.right);
            // x is right child
            // if has right child in turn, easy
            // --> DFS and store parent as successor
            DS.TreeNode succ = null;
            while (root != null) {
                if (root.val < x.val) {
                    root = root.right;
                } else if (root.val > x.val) {
                    succ = root;
                    root = root.left;
                } else break;
            }
            return succ;
        } else return null;
    }

    private static DS.TreeNode leftMost(DS.TreeNode root) {
        while(root != null && root.left != null) root = root.left;
        return root;
    }

    public static void main(String[] args) {
        DS.TreeNode Aa = new DS.TreeNode(6);
        Aa.left = new DS.TreeNode(3);
        Aa.left.left = new DS.TreeNode(2);
        Aa.left.right = new DS.TreeNode(4);
        Aa.left.right.right = new DS.TreeNode(5);
        Aa.right = new DS.TreeNode(8);
        Aa.right.left = new DS.TreeNode(7);
        Aa.right.right = new DS.TreeNode(10);
        Aa.right.right.left = new DS.TreeNode(9);
        System.out.println(inorder(Aa));
        System.out.println(inorderSuccessor(Aa, Aa.left.right).val);
    }
}
