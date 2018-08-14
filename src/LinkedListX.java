public class LinkedListX {
    Link first;
    Link last;

    public LinkedListX() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void displayList() {
        System.out.println("first --> last");
        Link current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
        System.out.println();
    }

    public void insertFirst(int v) {
        Link newLink = new Link(v);
        if (isEmpty()) {
            first = newLink;
            last = newLink;
        } else {
            newLink.next = first;
            first.prev = newLink;
            first = newLink;
        }
    }

    public void insertLast(int v) {
        Link newLink = new Link(v);
        if (isEmpty()) {
            first = newLink;
            last = newLink;
        }
//        else {
//            Link current = first;
//            while(current.next != null) current = current.next;
//            current.next = newLink;
//        }
        else {
            newLink.prev = last;
            last.next = newLink;
            last = newLink;
        }
    }

    public boolean delete(int v) {
        if (isEmpty()) {
            System.out.println("List empty - nothing to delete");
            return false;
        }
        Link current = first;
        Link previous = current;
        if (first.value == v) {
            //first.next.prev = null;
            first = first.next;
            return true;
        }
        current = current.next;
        while(current != null) {
            if (current.value == v) {
                previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Not found");
        return false;
    }
}

class Link {
    int value;
    Link next;
    Link prev;

    public Link(int v) {
        value = v;
        next = null;
        prev = null;
    }

    public void display() {
        System.out.print("[" + value + "] ");
    }
}
