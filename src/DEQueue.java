public class DEQueue {
    int maxSize;
    LinkedList queList;
    int nItems = 0;

    DEQueue(int size) {
        maxSize = size;
        queList = new LinkedList();
    }

    public void insertLeft(int value) {
        if (isFull()) System.out.println("Queue full. Cannot insert any more items");
        else {
            queList.insertFirst(value);
            nItems++;
        }

    }
    public void insertRight(int value) {
        if (isFull()) System.out.println("Queue full. Cannot insert any more items");
        else {
            queList.insertLast(value);
            nItems++;
        }
    }
    public Link removeLeft() {
        Link removed = queList.first;
        if (isEmpty()) {
            System.out.println("Queue empty. Nothing to remove");
            return removed;
        }
        queList.first = queList.first.next;
        if (queList.first != null) {
            queList.first.prev = null;
        }
        nItems--;
        return removed;
    }

    public Link removeRight() {
        Link removed = queList.last;
        if (isEmpty()) {
            System.out.println("Queue empty. Nothing to remove");
            return removed;
        }
        queList.last = queList.last.prev;
        if (queList.last != null) {
            queList.last.next = null;
        }
        nItems--;
        return removed;
    }

    public boolean isEmpty() {
        return queList.first == null || queList.last == null;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }
}
