public class PriorityQueueX {
    int maxSize;
    LinkedListX queList;
    int nItems = 0;

    PriorityQueueX(int size) {
        maxSize = size;
        queList = new LinkedListX();
    }

    public void add(int value) { // ordered list, asc. O(N)
        if (isEmpty() || value < queList.first.value) {
            queList.insertFirst(value);
        } else if (isFull()) {
            System.out.println("Queue full. Cannot insert any more items");
        } else {
            Link current = queList.first;
            Link previous = current;
            while(current != null && current.value <= value) {
                previous = current;
                current = current.next;
            }
            Link newLink = new Link(value);
            previous.next = newLink;
            newLink.next = current;
        }
        nItems++;
    }

    public Link remove() { // O(1)
        if (isEmpty()) {
            System.out.println("Cannot remove from empty queue");
            return null;
        }
        Link removed = queList.first;
        queList.first = queList.first.next;
        nItems--;
        return removed;
    }

    public boolean isEmpty() {
        return queList.first == null;
    }

    public boolean isFull() {
        return nItems == maxSize;
    }
}
