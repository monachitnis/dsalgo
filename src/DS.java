import java.util.ArrayList;

class DS {
    static class StackX {
        private ArrayList<Character> stackArr;
        private int top;

        public StackX() {
            stackArr = new ArrayList<>();
            top = -1;
        }

        public void push(char c) {
            // if (isFull()) {
            //     System.out.println("stack full, cannot push " + c);
            // } else {
            stackArr.add(c);
            top++;
            //}
        }

        public char pop() {
            if (isEmpty()) {
                System.out.println("stack empty, nothing to pop");
                return '-';
            } else {
                char p = stackArr.remove(top);
                top--;
                return p;
            }
        }

//     public boolean isFull() {
//         return top == stackArr.length;
//     }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    static class MinHeapX {
        private int[] heap;
        private int size;

        public MinHeapX(int size) {
            heap = new int[size];
            size = 0;
        }

        public MinHeapX(int[] elems) {
            heap = elems;
            size = elems.length;
            trickleUp(size-1);
        }

        public void add(int elem) {
            heap[size++] = elem;
        }

        private void trickleUp(int i) {
            int parent = (i-1)/2;
            if (heap[i] >= heap[parent]) return;
            //swap
            int temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;
            trickleUp(parent);
        }

        private void trickleDown(int i) {
            int leftChild = 2*i + 1;
            int rightChild = 2*i + 2;
            int smaller = i;
            //find the smaller child
            if(leftChild < size && rightChild < size) {
                smaller = heap[leftChild] < heap[rightChild] ? leftChild : rightChild;
            } else if (leftChild < size) {
                smaller = heap[leftChild] < heap[i] ? leftChild : i;
            } else if (rightChild < size) {
                smaller = heap[rightChild] < heap[i] ? rightChild : i;
            } else return; //nothing to do
            if(smaller != i) {
                //swap
                int temp = heap[i];
                heap[i] = heap[smaller];
                heap[smaller] = temp;
            }
            trickleDown(smaller);
        }

        public int[] heapSort() {
            int[] sorted = new int[size];
            for(int j = 0; j < size; j++) {
                sorted[j] = heap[0];
                heap[0] = heap[size-1];
                trickleDown(0);
            }
            return sorted;
        }

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }

        static TreeNode createTree() {
            TreeNode A = new TreeNode(1);
            A.left = new TreeNode(2);
            A.left.left = new TreeNode(3);
            A.left.left.left = new TreeNode(4);
            A.right = new TreeNode(2);
            A.right.right = new TreeNode(3);
            A.right.right.right = new TreeNode(4);

            return A;
        }
    }
}
