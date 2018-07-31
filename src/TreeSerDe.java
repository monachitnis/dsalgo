import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeSerDe {

    public static String serialize(DS.TreeNode root) {
        //trivial case
        List<String> ser = new ArrayList<>();
        if (root == null) return "";
        Queue<DS.TreeNode> que = new LinkedList<>();
        que.add(root);
        while (!que.isEmpty()) {
            DS.TreeNode t = que.remove();
            if (t == null) {
                ser.add("null");
            } else {
                ser.add(t.val + "");
                que.add(t.left);
                que.add(t.right);
            }
        }
        StringBuilder ret = new StringBuilder();
        //remove any trailing "nulls" at leaf level
        int i = ser.size() - 1;
        while (i >= 0) {
            if (ser.get(i).equals("null")) {
                i--;
            } else {
                break;
            }
        }
        for (int j = 0; j <= i; j++) {
            ret.append(",").append(ser.get(j));
        }
        String s = ret.toString().substring(1); //strip leading ,
        return "[" + s + "]";
    }

    public static DS.TreeNode deserialize(String data) {
        //tree from positions in array
        //trivial case
        DS.TreeNode root = null;
        if (data.isEmpty()) return root;
        String d = data.substring(1).substring(0, data.length() - 2); //strip square brackets [ ]
        String[] d1 = d.split(",");
        return helper(d1, 0);
    }

    public static DS.TreeNode helper(String[] data, int i) {
        DS.TreeNode curr = null;
        if (i < data.length) {
            String node = data[i];
            if (!node.equals("null")) {
                curr = new DS.TreeNode(Integer.parseInt(node));
                int leftChild = 2 * i + 1;
                int rightChild = 2 * i + 2;
                if (leftChild < data.length)
                    curr.left = helper(data, leftChild);
                if (rightChild < data.length)
                    curr.right = helper(data, rightChild);
            }
        }
        return curr;
    }

    public static void main(String[] args) {
        DS.TreeNode tree = DS.TreeNode.createTree();
        DS.TreeNode B = deserialize(serialize(tree));
        System.out.println(serialize(tree));
        System.out.println(serialize(B));
    }

}
