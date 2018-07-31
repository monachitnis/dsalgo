import java.util.ArrayList;

public class LinkListApp {
    public static void main(String[] args) {

        /*System.out.println("Vanilla LL");
        LinkedList ll = new LinkedList();
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
        PriorityQueue pq = new PriorityQueue(5);
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
    }
}
