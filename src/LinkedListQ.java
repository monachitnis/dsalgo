import java.util.ArrayList;

public class LinkedListQ {
    public static void main(String[] args) {

        /*System.out.println("Vanilla LL");
        LinkedListX ll = new LinkedListX();
        ll.insertLast(1);
        ll.insertLast(2);
        ll.insertLast(3);
        ll.insertLast(4);
        ll.insertLast(5);
        ll.insertLast(6);

        ll.displayList();
        ll.delete(3);
        ll.displayList();
        ll.insertLast(7);
        ll.displayList();

        System.out.println("Priority Q using LL");
        PriorityQueueX pq = new PriorityQueueX(5);
        pq.add(21);
        pq.add(20);
        pq.add(19);
        pq.add(23);
        pq.add(1);

        pq.queList.displayList();
        Link removed = pq.remove();
        assert (removed.value == 1);
        pq.add(25);
        pq.queList.displayList();
        removed = pq.remove();
        assert (removed.value == 19);
        pq.add(11);
        pq.add(11);
        pq.add(11);
        pq.queList.displayList();*/

        System.out.println("DE Q using LL");
        DEQueue deq = new DEQueue(5);
        deq.insertRight(1);
        deq.insertRight(2);
        deq.insertRight(3);
        deq.insertLeft(4);
        deq.insertLeft(5);
        System.out.println("Size=" + deq.nItems);
        deq.queList.displayList();
        Link removed = deq.removeLeft();
        assert(removed.value == 5);
        System.out.println("Size=" + deq.nItems);
        removed = deq.removeRight();
        assert(removed.value == 3);
        System.out.println("Size=" + deq.nItems);

        deq.queList.displayList(); //4,1,2
        removed = deq.removeRight();

        deq.queList.displayList(); //4,1
        removed = deq.removeRight();

        deq.queList.displayList();
        removed = deq.removeRight();
        deq.queList.displayList();

        char p = ')';
        char[] s1 = "()mona".toCharArray();
        for (char c : s1) {
            if (c == '(' && p != ')')
                System.out.println("open");
            else if (c == ')')
                System.out.println("close");
        }

        ArrayList<Character> a = new ArrayList<>();
        a.add('x');


        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(7);
        l1.next.next.next.next = new ListNode(9);
        l1.display();

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(6);
        l2.next.next.next = new ListNode(8);
        l2.next.next.next.next = new ListNode(10);
        l2.next.next.next.next.next = new ListNode(12);
        l2.display();

        ListNode merged = mergeSortedLinkedLists(l1, l2);
        merged.display();

        ListNode l3 = new ListNode(4);
        l3.next = new ListNode(2);
        l3.next.next = new ListNode(1);
        l3.next.next.next = new ListNode(3);
        l3.next.next.next.next = new ListNode(0);
        ListNode sorted = sortConstantSpace(l3);
        sorted.display();
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // corner cases
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        int sum = 0;
        int carry = 0;
        ListNode head = null;
        ListNode ptr = null;

        while(l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            carry = 0;
            if (sum >= 10) {
                sum = sum % 10;
                carry = 1;
            }
            ListNode sumn = new ListNode(sum);
            if (head == null) {
                head = sumn;
                ptr = head;
            } else {
                ptr.next = sumn;
                ptr = ptr.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if(l1 == null && l2 != null) {
            while(l2 != null) {
                sum = l2.val + carry;
                carry = 0;
                if (sum >= 10) {
                    sum = sum % 10;
                    carry = 1;
                }
                ListNode l2a = new ListNode(sum);
                ptr.next = l2a;
                ptr = ptr.next;
                l2 = l2.next;
            }
        } else if(l1 != null) {
            while(l1 != null) {
                sum = l1.val + carry;
                carry = 0;
                if (sum >= 10) {
                    sum = sum % 10;
                    carry = 1;
                }
                ListNode l1a = new ListNode(sum);
                ptr.next = l1a;
                ptr = ptr.next;
                l1 = l1.next;
            }
        }
        if (carry > 0) {
            ListNode lc = new ListNode(1);
            ptr.next = lc;
            ptr = ptr.next;
        }
        return head;
    }

    static ListNode mergeSortedLinkedLists(ListNode l1, ListNode l2) {
        // merge l1 and l2 in constant space
        // corner cases
        if (l2 == null) return l1;
        if (l1 == null) return l2;
        ListNode head;
        // init
        if(l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;
        } else {
            head = l2;
            l2 = l2.next;
        }
        ListNode curr = head;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        // smaller list ended
        if (l1 == null) curr.next = l2;
        else curr.next = l1;
        return head;
    }

    static ListNode sortConstantSpace(ListNode head) {
        // use mergesort - O(nlogn) complexity
        // traverse once to get length, then repeatedly divide length by 2
        int len = 0;
        if (head == null || head.next == null) return head;
        ListNode curr = head;
        while(curr != null) {
            len++;
            curr = curr.next;
        }
        return mergeSort(head, 0, len);
    }

    static ListNode mergeSort(ListNode l, int low, int high) {
        if(low >= high || l.next == null) return l;
        int mid = (low+high)/2;
        // get first and second halves of l
        ListNode l1 = l;
        int count = low;
        while(count < mid-1) {
            l1 = l1.next;
            count++;
        }
        ListNode l2 = l1.next;
        l1.next = null;
        ListNode half1 = mergeSort(l, low, mid);
        ListNode half2 = mergeSort(l2, mid, high);
        return mergeSortedLinkedLists(half1, half2);
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public void display() {
            ListNode current = this;
            while (current != null) {
                System.out.print(current.val);
                if (current.next != null) System.out.print(" -> ");
                current = current.next;
            }
            System.out.println();
        }
    }
}
